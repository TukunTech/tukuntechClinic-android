package pe.edu.upc.tukuntech.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import pe.edu.upc.tukuntech.data.models.PatientsCriticEntity

@Database(entities = [PatientsCriticEntity::class], version = 1, exportSchema = false)
abstract class AppDataBase: RoomDatabase() {
    abstract fun patientsCriticDao(): PatientCriticDao
}