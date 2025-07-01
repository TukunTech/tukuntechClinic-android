package pe.edu.upc.tukuntech.data.remote

import pe.edu.upc.tukuntech.data.models.PatientEntity
import retrofit2.http.GET

interface PatientService {
    @GET("patients/patients")
    suspend fun getAllPatients(): List<PatientEntity>
}