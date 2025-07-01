package pe.edu.upc.tukuntech.domain.models

data class Beds(
    val id: Int,
    val patientId: Int,
    val name: String,
    val lastName: String,
    val hr: String,
    val nipb: String,
    val spo02: String,
    val temp: String,
    var isCritic: Boolean = false,
)
