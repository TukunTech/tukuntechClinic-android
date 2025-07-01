package pe.edu.upc.tukuntech.domain.models

data class PatientWithBed(
    val id: Int,
    val fullName: String,
    val bedName: String?
)
