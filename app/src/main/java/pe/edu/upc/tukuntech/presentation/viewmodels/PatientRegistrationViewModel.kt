package pe.edu.upc.tukuntech.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pe.edu.upc.tukuntech.data.models.PatientEntity
import pe.edu.upc.tukuntech.data.repository.PatientRepository

class PatientRegistrationViewModel(
    private val repository: PatientRepository
) : ViewModel() {

    private val _registrationResult = MutableStateFlow<Result<Unit>?>(null)
    val registrationResult: StateFlow<Result<Unit>?> = _registrationResult

    fun registerPatient(patient: PatientEntity) {
        viewModelScope.launch {
            try {
                val success = repository.registerPatient(patient)
                if (success) {
                    _registrationResult.value = Result.success(Unit)
                } else {
                    _registrationResult.value = Result.failure(Exception("Error al registrar paciente"))
                }
            } catch (e: Exception) {
                _registrationResult.value = Result.failure(e)
            }
        }
    }
}