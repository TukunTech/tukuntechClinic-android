package pe.edu.upc.tukuntech.data.di

import androidx.room.Room
import pe.edu.upc.tukuntech.TukunTechApplication
import pe.edu.upc.tukuntech.data.local.AppDataBase
import pe.edu.upc.tukuntech.data.local.PatientCriticDao
import pe.edu.upc.tukuntech.data.remote.ApiConstants
import pe.edu.upc.tukuntech.data.remote.BedsService
import pe.edu.upc.tukuntech.data.repository.BedsRepository
import pe.edu.upc.tukuntech.data.repository.CriticalPatientsRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataModule {
    fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    fun getBedsService(): BedsService{
        return getRetrofit().create(BedsService::class.java)
    }
    fun getAppDatabase(): AppDataBase{
        return Room.databaseBuilder(TukunTechApplication.instance.applicationContext, AppDataBase::class.java, "tukun.db").build()
    }
    fun getPatientsDao(): PatientCriticDao{
        return getAppDatabase().patientsCriticDao()
    }
    fun getBedsRepository(): BedsRepository{
        return BedsRepository(getBedsService(), getPatientsDao())
    }
    fun getCriticalPatientsRepository(): CriticalPatientsRepository {
        return CriticalPatientsRepository(getPatientsDao())
    }
}