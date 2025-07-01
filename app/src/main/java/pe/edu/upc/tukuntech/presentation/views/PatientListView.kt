package pe.edu.upc.tukuntech.presentation.views

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pe.edu.upc.tukuntech.R
import pe.edu.upc.tukuntech.domain.models.PatientWithBed
import pe.edu.upc.tukuntech.presentation.viewmodels.PatientListViewModel

@Composable
fun PatientListView(navController: NavController, viewModel: PatientListViewModel) {
    val patients by viewModel.patientWithBeds.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadPatients()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.tukuntech),
                contentDescription = "Logo",
                modifier = Modifier.size(48.dp)
            )
            Text(
                text = "Patients",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF004F72)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Filter Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Card(
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0091AC))
            ) {
                Text(
                    text = "Patient",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth(),
                    maxLines = 1,
                    softWrap = false,
                    textAlign = TextAlign.Center
                )
            }
            Card(
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0091AC))
            ) {
                Text(
                    text = "Bed",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth(),
                    maxLines = 1,
                    softWrap = false,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Loader or Patient List
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 100.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CircularProgressIndicator(color = Color(0xFF0091AC))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Loading patients...", color = Color(0xFF0091AC))
                }
            }
        } else {
            LazyColumn {
                items(patients) { patient ->
                    PatientRow(
                        name = patient.fullName,
                        bedId = patient.bedName,
                        dni = patient.dni,
                        age = patient.age,
                        bloodType = patient.bloodType,
                        nationality = patient.nationality,
                        gender = patient.gender
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }

        }

        Spacer(modifier = Modifier.weight(1f))

        // Floating Button
        FloatingActionButton(
            onClick = {
                navController.navigate("patientRegistration") {
                    launchSingleTop = true
                }
            },
            containerColor = Color(0xFF0091AC),
            modifier = Modifier
                .align(Alignment.End)
                .size(56.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Agregar paciente",
                tint = Color.White
            )
        }
    }
}

@Composable
fun PatientRow(
    name: String,
    bedId: String?,
    dni: String,
    age: Int,
    bloodType: String,
    nationality: String,
    gender: String
) {
    var expanded by remember { mutableStateOf(false) }

    val rotation by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        label = "ArrowRotation"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { expanded = !expanded },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8F8F8)),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Row {
                    Text(
                        text = bedId ?: "Unassigned",
                        fontSize = 14.sp,
                        color = if (bedId == null) Color.Red else Color(0xFF004F72)
                    )
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Expand",
                        modifier = Modifier
                            .size(24.dp)
                            .graphicsLayer {
                                rotationZ = rotation
                            }
                    )
                }
            }

            if (expanded) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFF1F9FF))
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    PatientDetail(label = "DNI", value = dni)
                    PatientDetail(label = "Age", value = age.toString())
                    PatientDetail(label = "Blood Type", value = bloodType)
                    PatientDetail(label = "Nationality", value = nationality)
                    PatientDetail(label = "Gender", value = gender)
                }
            }

        }
    }
}

@Composable
fun PatientDetail(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = "$label: ",
            fontWeight = FontWeight.Bold,
            color = Color(0xFF004F72),
            modifier = Modifier.width(100.dp)
        )
        Text(text = value)
    }
}

