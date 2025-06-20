package pe.edu.upc.tukuntech.data.remote

import pe.edu.upc.tukuntech.data.models.BedsResponse
import retrofit2.Response
import retrofit2.http.GET

interface BedsService {
    @GET("api/v1/bed")
    suspend fun getBeds():Response<List<BedsResponse>>
}