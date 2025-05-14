package pe.edu.upc.tukuntech.domain.models

data class Patient (
    val bed: String,
    val hr: Int,
    val nibp: String,
    val spo2: Int,
    val temp: Double
)