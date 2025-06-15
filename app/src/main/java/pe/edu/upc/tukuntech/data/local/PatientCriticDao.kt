package pe.edu.upc.tukuntech.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import pe.edu.upc.tukuntech.data.models.PatientsCriticEntity

@Dao
interface PatientCriticDao {
    @Insert
    suspend fun insertPatientCritic(entity: PatientsCriticEntity)

    @Delete
    suspend fun deletePatientCritic(entity: PatientsCriticEntity)

    @Query("SELECT * FROM patients_critic WHERE id = :id")
    suspend fun getPatientCriticById(id: Int): PatientsCriticEntity?

    @Query("SELECT * FROM patients_critic")
    suspend fun getAllPatientsCritics(): List<PatientsCriticEntity>

}