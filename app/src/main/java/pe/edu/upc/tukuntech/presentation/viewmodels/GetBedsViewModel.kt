package pe.edu.upc.tukuntech.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pe.edu.upc.tukuntech.data.repository.BedsRepository
import pe.edu.upc.tukuntech.domain.models.Patients

class GetBedsViewModel(private val bedsRepository: BedsRepository) : ViewModel() {
    private val _beds = MutableStateFlow<List<Patients>>(emptyList())
    val beds: StateFlow<List<Patients>> = _beds

    init {
        getBeds()
    }

    fun getBeds() {
        viewModelScope.launch {
            _beds.value = bedsRepository.getBeds()
        }
    }

    fun insertPatientCritic(patients: Patients){
        viewModelScope.launch {
            bedsRepository.insertPatientCritic(patients)
        }
    }

    fun deletePatientCritic(patients: Patients) {
        viewModelScope.launch {
            bedsRepository.deletePatientCritic(patients)
        }
    }

}
