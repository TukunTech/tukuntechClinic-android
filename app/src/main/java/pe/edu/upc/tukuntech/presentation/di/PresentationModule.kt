package pe.edu.upc.tukuntech.presentation.di

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