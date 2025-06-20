package pe.edu.upc.tukuntech.presentation.di

import pe.edu.upc.tukuntech.data.remote.AuthService
import pe.edu.upc.tukuntech.data.repository.AuthRepository
import pe.edu.upc.tukuntech.presentation.viewmodels.AuthViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PresentationModule {
    fun getAuthService(): AuthService {
    return Retrofit.Builder()
        .baseUrl("https://tukun-tech-platform.onrender.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(AuthService::class.java)
}

    fun getAuthViewModel(): AuthViewModel {
        return AuthViewModel(AuthRepository(getAuthService()))
import pe.edu.upc.tukuntech.data.di.DataModule
import pe.edu.upc.tukuntech.presentation.viewmodels.CriticalPatientsListViewModel
import pe.edu.upc.tukuntech.presentation.viewmodels.GetBedsViewModel

object PresentationModule {
    fun getBedsViewModel(): GetBedsViewModel{
        return GetBedsViewModel(DataModule.getBedsRepository())
    }
    fun getCriticalPatientsViewModel(): CriticalPatientsListViewModel{
        return CriticalPatientsListViewModel(DataModule.getCriticalPatientsRepository())
    }
}