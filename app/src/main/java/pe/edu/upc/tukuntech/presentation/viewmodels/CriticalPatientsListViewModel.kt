package pe.edu.upc.tukuntech.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pe.edu.upc.tukuntech.data.repository.CriticalPatientsRepository
import pe.edu.upc.tukuntech.domain.models.CriticalPatient

class CriticalPatientsListViewModel(
    private val patientsCriticalRepository: CriticalPatientsRepository
) : ViewModel() {

    private val _criticalPatients = MutableStateFlow<List<CriticalPatient>>(emptyList())
    val criticalPatients: StateFlow<List<CriticalPatient>> = _criticalPatients

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun fetchCriticalPatients() {
        viewModelScope.launch {
            _isLoading.value = true
            _criticalPatients.value = patientsCriticalRepository.fetchCriticalPatients()
            _isLoading.value = false
        }
    }

    fun deleteCriticalPatients(criticalPatient: CriticalPatient) {
        viewModelScope.launch {
            patientsCriticalRepository.deleteCriticalPatient(criticalPatient)
            fetchCriticalPatients()
        }
    }
}
