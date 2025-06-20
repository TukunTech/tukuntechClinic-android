package pe.edu.upc.tukuntech.domain.models

data class CriticalPatient (
    val id: Int,
    val name: String,
    val lastName: String,
    val hr: String,
    val nipb: String,
    val spo02: String,
    val temp: String
)