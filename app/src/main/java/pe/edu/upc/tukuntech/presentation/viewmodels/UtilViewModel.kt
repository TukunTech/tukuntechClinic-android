package pe.edu.upc.tukuntech.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pe.edu.upc.tukuntech.data.models.Allergy
import pe.edu.upc.tukuntech.data.models.BloodType
import pe.edu.upc.tukuntech.data.models.Gender
import pe.edu.upc.tukuntech.data.models.MedicalInsurance
import pe.edu.upc.tukuntech.data.models.Nationality
import pe.edu.upc.tukuntech.data.repository.UtilRepository

class UtilViewModel(private val repository: UtilRepository) : ViewModel() {

    private val _genders = MutableStateFlow<List<Gender>>(emptyList())
    val genders: StateFlow<List<Gender>> = _genders

    private val _bloodTypes = MutableStateFlow<List<BloodType>>(emptyList())
    val bloodTypes: StateFlow<List<BloodType>> = _bloodTypes

    private val _nationalities = MutableStateFlow<List<Nationality>>(emptyList())
    val nationalities: StateFlow<List<Nationality>> = _nationalities

    private val _medicalInsurances = MutableStateFlow<List<MedicalInsurance>>(emptyList())
    val medicalInsurances: StateFlow<List<MedicalInsurance>> = _medicalInsurances

    private val _allergies = MutableStateFlow<List<Allergy>>(emptyList())
    val allergies: StateFlow<List<Allergy>> = _allergies

    fun loadAllUtilData() {
        viewModelScope.launch {
            _genders.value = repository.getGenders()
            _bloodTypes.value = repository.getBloodTypes()
            _nationalities.value = repository.getNationalities()
            _medicalInsurances.value = repository.getMedicalInsurances()
            _allergies.value = repository.getAllergies()
        }
    }
}