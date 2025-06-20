package pe.edu.upc.tukuntech.data.remote


import pe.edu.upc.tukuntech.data.models.LoginRequest
import pe.edu.upc.tukuntech.data.models.LoginResponse
import pe.edu.upc.tukuntech.data.models.RegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("/api/v1/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
    @POST("/api/v1/auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<Void>
}
