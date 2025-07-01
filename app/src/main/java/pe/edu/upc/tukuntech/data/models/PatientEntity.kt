package pe.edu.upc.tukuntech.data.models

data class PatientEntity(
    val id: Int,
    val name: String,
    val lastName: String,
    val dni: String,
    val age: Int,
    val gender: Gender,
    val bloodType: BloodType,
    val nationality: Nationality,
    val medicalInsurance: MedicalInsurance?,
    val allergy: Allergy?
)

data class Gender(val id: Int, val gender: String)
data class BloodType(val id: Int, val type: String)
data class Nationality(val id: Int, val nationality: String)
data class MedicalInsurance(val id: Int, val medical: String)
data class Allergy(val id: Int, val allergies: String)
