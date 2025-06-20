package pe.edu.upc.tukuntech.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pe.edu.upc.tukuntech.data.local.PatientCriticDao
import pe.edu.upc.tukuntech.data.models.PatientsCriticEntity
import pe.edu.upc.tukuntech.domain.models.CriticalPatient

class CriticalPatientsRepository(private val patientCriticDao: PatientCriticDao) {
    suspend fun fetchCriticalPatients(): List<CriticalPatient> = withContext(Dispatchers.IO) {
        return@withContext patientCriticDao.getAllPatientsCritics().map {
            CriticalPatient(
                id = it.id,
                name = it.name,
                lastName = it.lastName,
                hr = it.hr,
                nipb = it.nipb,
                spo02 = it.spo02,
                temp = it.temp,

            )
        }
        return@withContext emptyList()
    }

    suspend fun deleteCriticalPatient(criticalPatient: CriticalPatient) = withContext(Dispatchers.IO) {
        patientCriticDao.deletePatientCritic(PatientsCriticEntity(
            id = criticalPatient.id,
            name = criticalPatient.name,
            lastName = criticalPatient.lastName,
            hr = criticalPatient.hr,
            nipb = criticalPatient.nipb,
            spo02 = criticalPatient.spo02,
            temp = criticalPatient.temp
        ))
    }
}