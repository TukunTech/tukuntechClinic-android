package pe.edu.upc.tukuntech.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pe.edu.upc.tukuntech.data.repository.BedsRepository
import pe.edu.upc.tukuntech.domain.models.Beds

class GetBedsViewModel(private val bedsRepository: BedsRepository) : ViewModel() {
    private val _beds = MutableStateFlow<List<Beds>>(emptyList())
    val beds: StateFlow<List<Beds>> = _beds

    init {
        getBeds()
    }

    fun getBeds() {
        viewModelScope.launch {
            _beds.value = bedsRepository.getBeds()
        }
    }

    fun insertPatientCritic(beds: Beds){
        viewModelScope.launch {
            bedsRepository.insertPatientCritic(beds)
        }
    }

    fun deletePatientCritic(beds: Beds) {
        viewModelScope.launch {
            bedsRepository.deletePatientCritic(beds)
        }
    }

}
