package pe.edu.upc.tukuntech.domain.models

data class PatientWithBed(
    val id: Int,
    val fullName: String,
    val bedName: String? = null,
    val dni: String,
    val age: Int,
    val gender: String,
    val bloodType: String,
    val nationality: String
)
