package pe.edu.upc.tukuntech.data.repository

import pe.edu.upc.tukuntech.data.models.PatientEntity
import pe.edu.upc.tukuntech.data.remote.PatientService

class PatientRepository(private val service: PatientService) {
    suspend fun getAllPatients(): List<PatientEntity> {
        return service.getAllPatients()
    }
}
