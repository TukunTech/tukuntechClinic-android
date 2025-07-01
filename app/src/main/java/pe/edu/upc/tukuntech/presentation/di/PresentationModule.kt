package pe.edu.upc.tukuntech.presentation.di

import pe.edu.upc.tukuntech.data.remote.AuthService
import pe.edu.upc.tukuntech.data.repository.AuthRepository
import pe.edu.upc.tukuntech.presentation.viewmodels.AuthViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import pe.edu.upc.tukuntech.data.di.DataModule
import pe.edu.upc.tukuntech.data.remote.PatientService
import pe.edu.upc.tukuntech.data.remote.UtilService
import pe.edu.upc.tukuntech.data.repository.PatientRepository
import pe.edu.upc.tukuntech.data.repository.UtilRepository
import pe.edu.upc.tukuntech.presentation.viewmodels.CriticalPatientsListViewModel
import pe.edu.upc.tukuntech.presentation.viewmodels.GetBedsViewModel
import pe.edu.upc.tukuntech.presentation.viewmodels.PatientListViewModel
import pe.edu.upc.tukuntech.presentation.viewmodels.PatientRegistrationViewModel
import pe.edu.upc.tukuntech.presentation.viewmodels.UtilViewModel

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

    }
    fun getBedsViewModel(): GetBedsViewModel {
        return GetBedsViewModel(DataModule.getBedsRepository())
    }

    fun getPatientService(): PatientService {
        return Retrofit.Builder()
            .baseUrl("https://tukun-tech-platform.onrender.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PatientService::class.java)
    }

    fun getPatientListViewModel(): PatientListViewModel {
        return PatientListViewModel(
            bedsRepository = DataModule.getBedsRepository(),
            patientRepository = getPatientRepository()
        )
    }


    fun getPatientRepository(): PatientRepository {
        return PatientRepository(getPatientService())
    }


    fun getCriticalPatientsViewModel(): CriticalPatientsListViewModel {
        return CriticalPatientsListViewModel(DataModule.getCriticalPatientsRepository())
    }

    fun getPatientRegistrationViewModel(): PatientRegistrationViewModel {
        return PatientRegistrationViewModel(getPatientRepository())
    }

    fun getUtilService(): UtilService {
        return Retrofit.Builder()
            .baseUrl("https://tukun-tech-platform.onrender.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UtilService::class.java)
    }

    fun getUtilRepository(): UtilRepository {
        return UtilRepository(getUtilService())
    }

    fun getUtilViewModel(): UtilViewModel {
        return UtilViewModel(getUtilRepository())
    }
}


