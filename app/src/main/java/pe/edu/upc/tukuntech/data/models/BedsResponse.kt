package pe.edu.upc.tukuntech.data.models

import pe.edu.upc.tukuntech.domain.models.Patients

data class BedsResponse(
    val id: Int?,
    val hr: Int?,
    val nipb: String?,
    val sp02: Int?,
    val temp: Double?,
    val patient: PatientResponse?,
    val bloodType: BloodTypeResponse?,
    val nationality: NationalityResponse?,
    val medicalInsurance: MedicalInsuranceResponse?,
    val allergy: AllergyResponse?

){
    fun toBeds() : Patients{
        return Patients(
            id = id?: 0,
            name = patient?.name ?: "",
            lastName = patient?.lastName ?: "",
            hr = hr?.toString() ?: "",
            nipb = nipb ?: "",
            spo02 = sp02?.toString() ?: "",
            temp = temp?.toString() ?: ""

        )
    }
}
data class PatientResponse(
    val id: Int?,
    val name: String?,
    val lastName: String?,
    val dni: String?,
    val age: Int?,
    val gender: GenderResponse?,
)
data class GenderResponse(
    val id: Int?,
    val gender: String?,
)
data class BloodTypeResponse(
    val id: Int?,
    val type: String?,
)
data class NationalityResponse(
    val id: Int?,
    val nationality: String?
)
data class MedicalInsuranceResponse(
    val id: Int?,
    val medical: String?
)
data class AllergyResponse(
    val id: Int?,
    val allergies: String?
)