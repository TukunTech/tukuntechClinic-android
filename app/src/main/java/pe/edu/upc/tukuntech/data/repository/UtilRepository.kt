package pe.edu.upc.tukuntech.data.repository

import pe.edu.upc.tukuntech.data.models.Allergy
import pe.edu.upc.tukuntech.data.models.BloodType
import pe.edu.upc.tukuntech.data.models.Gender
import pe.edu.upc.tukuntech.data.models.MedicalInsurance
import pe.edu.upc.tukuntech.data.models.Nationality
import pe.edu.upc.tukuntech.data.remote.UtilService

class UtilRepository(private val service: UtilService) {

    suspend fun getGenders(): List<Gender> {
        return service.getGenderList()
    }

    suspend fun getBloodTypes(): List<BloodType> {
        return service.getBloodTypeList()
    }

    suspend fun getNationalities(): List<Nationality> {
        return service.getNationalityList()
    }

    suspend fun getMedicalInsurances(): List<MedicalInsurance> {
        return service.getMedicalInsuranceList()
    }

    suspend fun getAllergies(): List<Allergy> {
        return service.getAllergyList()
    }
}