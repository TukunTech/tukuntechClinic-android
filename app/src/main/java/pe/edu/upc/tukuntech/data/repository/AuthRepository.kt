package pe.edu.upc.tukuntech.data.repository

import pe.edu.upc.tukuntech.data.models.LoginRequest
import pe.edu.upc.tukuntech.data.models.LoginResponse
import pe.edu.upc.tukuntech.data.models.RegisterRequest
import pe.edu.upc.tukuntech.data.remote.AuthService

// AuthRepository.kt
class AuthRepository(private val authService: AuthService) {

    suspend fun login(username: String, password: String): Result<String> {
        return try {
            val response = authService.login(LoginRequest(username, password))
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!.token)
            } else {
                Result.failure(Exception("Login fallido: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun register(
        username: String,
        password: String,
        email: String,
        firstName: String,
        lastName: String
    ): Result<Unit> {
        return try {
            val request = RegisterRequest(username, password, email, firstName, lastName)
            val response = authService.register(request)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Registro fallido: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
