package pe.edu.upc.tukuntech.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pe.edu.upc.tukuntech.data.repository.BedsRepository
import pe.edu.upc.tukuntech.data.repository.PatientRepository
import pe.edu.upc.tukuntech.domain.models.PatientWithBed

class PatientListViewModel(
    private val bedsRepository: BedsRepository,
    private val patientRepository: PatientRepository
) : ViewModel() {

    private val _patientWithBeds = MutableStateFlow<List<PatientWithBed>>(emptyList())
    val patientWithBeds: StateFlow<List<PatientWithBed>> = _patientWithBeds

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun loadPatients() {
        viewModelScope.launch {
            _isLoading.value = true

            val patients = patientRepository.getAllPatients()
            val beds = bedsRepository.getBeds()

            val result = patients.map { patient ->
                val assignedBed = beds.find { it.patientId == patient.id }

                PatientWithBed(
                    id = patient.id,
                    fullName = "${patient.name} ${patient.lastName}",
                    bedName = assignedBed?.let { "Bed ${it.id + 1}" },
                    dni = patient.dni,
                    age = patient.age,
                    gender = patient.gender.gender,
                    bloodType = patient.bloodType.type,
                    nationality = patient.nationality.nationality
                )
            }


            _patientWithBeds.value = result
            _isLoading.value = false
        }
    }
}
