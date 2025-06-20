package pe.edu.upc.tukuntech.data.repository

import pe.edu.upc.tukuntech.data.models.LoginRequest
import pe.edu.upc.tukuntech.data.models.RegisterRequest
import pe.edu.upc.tukuntech.data.remote.AuthService

class AuthRepository(private val authService: AuthService) {

    suspend fun login(username: String, password: String): Result<String> {
        return try {
            val request = LoginRequest(username, password)
            println("🟡 Enviando login request: $request")
            val response = authService.login(request)

            println("🔴 Código de respuesta: ${response.code()}")
            println("🔴 Body: ${response.body()}")
            println("🔴 Error body: ${response.errorBody()?.string()}")

            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!.token)
            } else {
                // ⚠️ Forzar el login aunque el backend diga que está mal
                println("⚠️ Login fallido con código ${response.code()}, pero se permite igual.")
                Result.success("forced-token-for-testing")
            }
        } catch (e: Exception) {
            // ⚠️ También se permite login aunque haya excepción
            println("⚠️ Excepción durante login: ${e.message}, pero se permite igual.")
            Result.success("forced-token-on-error")
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
