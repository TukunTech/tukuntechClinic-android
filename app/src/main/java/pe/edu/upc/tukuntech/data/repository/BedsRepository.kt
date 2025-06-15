package pe.edu.upc.tukuntech.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pe.edu.upc.tukuntech.data.local.PatientCriticDao
import pe.edu.upc.tukuntech.data.models.PatientsCriticEntity
import pe.edu.upc.tukuntech.data.remote.BedsService
import pe.edu.upc.tukuntech.domain.models.Patients

class BedsRepository(private val bedsService: BedsService, private val patientCriticDao: PatientCriticDao) {
    suspend fun getBeds(): List<Patients> =
        withContext(Dispatchers.IO) {
            val response = bedsService.getBeds()
            if (response.isSuccessful) {
                response.body()?.let { it ->
                    return@withContext it.map { bedsResponse ->
                        val beds = bedsResponse.toBeds()
                        beds.copy(
                            isCritic = patientCriticDao.getAllPatientsCritics().isNotEmpty()
                        )
                    }
                }
            }
            return@withContext emptyList()
        }
    suspend fun insertPatientCritic(patient: Patients) = withContext(Dispatchers.IO) {
        patientCriticDao.insertPatientCritic(
            PatientsCriticEntity(
                id = patient.id,
                name = patient.name,
                lastName = patient.lastName,
                hr = patient.hr,
                nipb = patient.nipb,
                spo02 = patient.spo02,
                temp = patient.temp

            )
        )
    }

    suspend fun deletePatientCritic(patient: Patients) = withContext(Dispatchers.IO) {
        patientCriticDao.deletePatientCritic(
            PatientsCriticEntity(
                id = patient.id,
                name = patient.name,
                lastName = patient.lastName,
                hr = patient.hr,
                nipb = patient.nipb,
                spo02 = patient.spo02,
                temp = patient.temp

            )
        )
    }


}