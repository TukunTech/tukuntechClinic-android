// data/models/RegisterRequest.kt
package pe.edu.upc.tukuntech.data.models

data class RegisterRequest(
    val username: String,
    val password: String,
    val email: String,
    val firstName: String,
    val lastName: String
)
