package pe.edu.upc.tukuntech

import android.app.Application

class TukunTechApplication: Application(){
    companion object{
        lateinit var instance: TukunTechApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}