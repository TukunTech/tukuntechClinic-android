package pe.edu.upc.tukuntech.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "patients_critic")
data class PatientsCriticEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val lastName: String,
    val hr: String,
    val nipb: String,
    val spo02: String,
    val temp: String,
)
