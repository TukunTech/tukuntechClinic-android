package pe.edu.upc.tukuntech.presentation.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pe.edu.upc.tukuntech.R
import pe.edu.upc.tukuntech.domain.models.CriticalPatient
import pe.edu.upc.tukuntech.presentation.viewmodels.CriticalPatientsListViewModel

@Composable
fun ICUView(viewModel: CriticalPatientsListViewModel) {
    val criticalPatients by viewModel.criticalPatients.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchCriticalPatients()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        when {
            isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color(0xFF0091AC)
                )
            }

            criticalPatients.isEmpty() -> {
                Text(
                    text = "No critical patients",
                    fontSize = 18.sp,
                    color = Color.Gray,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            else -> {
                Column {
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
                            text = "Critical Patients",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF004F72)

                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    LazyColumn {
                        items(criticalPatients) { patient ->
                            CriticalPatientListItemView(patient) {
                                viewModel.deleteCriticalPatients(patient)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CriticalPatientListItemView(
    patient: CriticalPatient,
    onDelete: (CriticalPatient) -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .background(Color(0xFFF7F7F7))
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Bed: ${patient.id} - ${patient.name} ${patient.lastName}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                IconButton(
                    onClick = { onDelete(patient) },
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.White)
                        .size(36.dp)
                ) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "Delete Patient",
                        tint = Color.Black
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            VitalSign(label = "HR", value = patient.hr, unit = "bpm", color = Color.Red)
            VitalSign(label = "NIPB", value = patient.nipb, unit = "mmHg", color = Color(0xFF2E7D32))
            VitalSign(label = "SpO2", value = patient.spo02, unit = "%", color = Color(0xFF6A1B9A))
            VitalSign(label = "Temp", value = patient.temp, unit = "°C", color = Color.Gray)
        }
    }
}
