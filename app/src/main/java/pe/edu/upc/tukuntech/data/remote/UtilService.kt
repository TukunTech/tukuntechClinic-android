package pe.edu.upc.tukuntech.data.remote

import pe.edu.upc.tukuntech.data.models.Allergy
import pe.edu.upc.tukuntech.data.models.BloodType
import pe.edu.upc.tukuntech.data.models.Gender
import pe.edu.upc.tukuntech.data.models.MedicalInsurance
import pe.edu.upc.tukuntech.data.models.Nationality
import retrofit2.http.GET

interface UtilService {

    @GET("/api/v1/util/listGender")
    suspend fun getGenderList(): List<Gender>

    @GET("/api/v1/util/listBlood")
    suspend fun getBloodTypeList(): List<BloodType>

    @GET("/api/v1/util/listNationality")
    suspend fun getNationalityList(): List<Nationality>

    @GET("/api/v1/util/listMedicalInsurance")
    suspend fun getMedicalInsuranceList(): List<MedicalInsurance>

    @GET("/api/v1/util/listAllergy")
    suspend fun getAllergyList(): List<Allergy>
}