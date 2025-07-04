package pe.edu.upc.tukuntech.presentation.views

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pe.edu.upc.tukuntech.R
import pe.edu.upc.tukuntech.data.models.Allergy
import pe.edu.upc.tukuntech.data.models.BloodType
import pe.edu.upc.tukuntech.data.models.Gender
import pe.edu.upc.tukuntech.data.models.MedicalInsurance
import pe.edu.upc.tukuntech.data.models.Nationality
import pe.edu.upc.tukuntech.data.models.PatientEntity
import pe.edu.upc.tukuntech.presentation.di.PresentationModule

@Composable
fun PatientRegistrationView(navController: NavController) {
    val scrollState = rememberScrollState()
    val inputBackground = Color(0xFFF5F5F5)
    val fieldPadding = 8.dp
    val context = LocalContext.current

    var name by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var dni by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var insurance by remember { mutableStateOf("") }
    var allergies by remember { mutableStateOf("") }

    val viewModel = remember { PresentationModule.getPatientRegistrationViewModel() }
    val registrationResult = viewModel.registrationResult.collectAsState()

    val utilViewModel = remember { PresentationModule.getUtilViewModel() }

    val genders by utilViewModel.genders.collectAsState()
    val bloodTypes by utilViewModel.bloodTypes.collectAsState()
    val nationalities by utilViewModel.nationalities.collectAsState()

    var selectedGender by remember { mutableStateOf<Gender?>(null) }
    var selectedBloodType by remember { mutableStateOf<BloodType?>(null) }
    var selectedNationality by remember { mutableStateOf<Nationality?>(null) }
    var selectedInsurance by remember { mutableStateOf<MedicalInsurance?>(null) }
    var selectedAllergy by remember { mutableStateOf<Allergy?>(null) }

    LaunchedEffect(Unit) {
        utilViewModel.loadAllUtilData()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
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
                text = "Patient Registration",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        CustomInputField("Name", name, inputBackground) { name = it }
        Spacer(modifier = Modifier.height(fieldPadding))

        CustomInputField("Last Name", lastName, inputBackground) { lastName = it }
        Spacer(modifier = Modifier.height(fieldPadding))

        CustomInputField("DNI", dni, inputBackground) { dni = it }
        Spacer(modifier = Modifier.height(fieldPadding))

        CustomInputField("Age", age, inputBackground) { age = it }
        Spacer(modifier = Modifier.height(fieldPadding))

        DropdownSelector(
            label = "Gender",
            selectedOption = selectedGender?.gender ?: "",
            options = genders.map { it.gender },
            onSelect = { selectedGender = genders[it] },
            backgroundColor = inputBackground
        )
        Spacer(modifier = Modifier.height(fieldPadding))

        DropdownSelector(
            label = "Blood Type",
            selectedOption = selectedBloodType?.type ?: "",
            options = bloodTypes.map { it.type },
            onSelect = { selectedBloodType = bloodTypes[it] },
            backgroundColor = inputBackground
        )
        Spacer(modifier = Modifier.height(fieldPadding))

        DropdownSelector(
            label = "Nationality",
            selectedOption = selectedNationality?.nationality ?: "",
            options = nationalities.map { it.nationality },
            onSelect = { selectedNationality = nationalities[it] },
            backgroundColor = inputBackground
        )
        Spacer(modifier = Modifier.height(fieldPadding))

        CustomInputField("Medical Insurance", insurance, inputBackground) { insurance = it }
        Spacer(modifier = Modifier.height(fieldPadding))

        CustomInputField("Allergies", allergies, inputBackground) { allergies = it }
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val ageInt = age.toIntOrNull()

                when {
                    name.isBlank() -> {
                        Toast.makeText(context, "El campo 'Name' es obligatorio", Toast.LENGTH_SHORT).show(); return@Button
                    }
                    lastName.isBlank() -> {
                        Toast.makeText(context, "El campo 'Last Name' es obligatorio", Toast.LENGTH_SHORT).show(); return@Button
                    }
                    dni.isBlank() -> {
                        Toast.makeText(context, "El campo 'DNI' es obligatorio", Toast.LENGTH_SHORT).show(); return@Button
                    }
                    dni.length != 8 -> {
                        Toast.makeText(context, "El DNI debe tener exactamente 8 dígitos", Toast.LENGTH_SHORT).show(); return@Button
                    }
                    age.isBlank() -> {
                        Toast.makeText(context, "El campo 'Age' es obligatorio", Toast.LENGTH_SHORT).show(); return@Button
                    }
                    ageInt == null || ageInt <= 0 -> {
                        Toast.makeText(context, "La edad debe ser un número válido mayor a 0", Toast.LENGTH_SHORT).show(); return@Button
                    }
                    selectedGender == null -> {
                        Toast.makeText(context, "Debes seleccionar un género", Toast.LENGTH_SHORT).show(); return@Button
                    }
                    selectedBloodType == null -> {
                        Toast.makeText(context, "Debes seleccionar un tipo de sangre", Toast.LENGTH_SHORT).show(); return@Button
                    }
                    selectedNationality == null -> {
                        Toast.makeText(context, "Debes seleccionar una nacionalidad", Toast.LENGTH_SHORT).show(); return@Button
                    }
                }

                val patient = PatientEntity(
                    id = 0,
                    name = name,
                    lastName = lastName,
                    dni = dni,
                    age = ageInt!!,
                    gender = selectedGender ?: Gender(0, ""),
                    bloodType = selectedBloodType ?: BloodType(0, ""),
                    nationality = selectedNationality ?: Nationality(0, ""),
                    medicalInsurance = selectedInsurance,
                    allergy = selectedAllergy
                )
                viewModel.registerPatient(patient)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00BCD4))
        ) {
            Text("Save", color = Color.White, modifier = Modifier.padding(vertical = 8.dp))
        }

        registrationResult.value?.let {
            if (it.isSuccess) {
                Text("✅ Paciente registrado correctamente", color = Color.Green)
            } else {
                Text("❌ Error al registrar: ${it.exceptionOrNull()?.message}", color = Color.Red)
            }
        }

        LaunchedEffect(registrationResult.value) {
            registrationResult.value?.onSuccess {
                navController.popBackStack()
            }
        }
    }
}

@Composable
fun CustomInputField(
    label: String,
    value: String,
    backgroundColor: Color,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = backgroundColor,
            unfocusedContainerColor = backgroundColor,
            focusedIndicatorColor = Color(0xFF00BCD4),
            unfocusedIndicatorColor = Color.Gray
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownSelector(
    label: String,
    selectedOption: String,
    options: List<String>,
    onSelect: (Int) -> Unit,
    backgroundColor: Color
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        TextField(
            value = selectedOption,
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            trailingIcon = { Icon(Icons.Filled.ArrowDropDown, contentDescription = null) },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = backgroundColor,
                unfocusedContainerColor = backgroundColor,
                focusedIndicatorColor = Color(0xFF00BCD4),
                unfocusedIndicatorColor = Color.Gray
            )
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEachIndexed { index, option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onSelect(index)
                        expanded = false
                    }
                )
            }
        }
    }
}
