package pe.edu.upc.tukuntech.domain.models

data class Patients(
    val id: Int,
    val name: String,
    val lastName: String,
    val hr: String,
    val nipb: String,
    val spo02: String,
    val temp: String,
    var isCritic: Boolean = false,
)
