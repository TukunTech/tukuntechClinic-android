package pe.edu.upc.tukuntech.data.remote

import pe.edu.upc.tukuntech.data.models.PatientEntity
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PatientService {
    @GET("patients/patients")
    suspend fun getAllPatients(): List<PatientEntity>

    @POST("/api/v1/patients")
    suspend fun createPatient(@Body patient: PatientEntity): Response<Unit>
}