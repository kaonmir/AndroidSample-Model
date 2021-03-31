package xyz.kaonmir.model.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import xyz.kaonmir.model.data.entities.SoldierModel
import xyz.kaonmir.model.data.repositories.SoldierRepository
import xyz.kaonmir.model.domain.usecases.SoldierUseCase

// todo(disposable checking)
// todo(fail to make viewModel to dependency injection

class SoldierViewModel(application: Application): AndroidViewModel(application) {
    private val soldierUseCase: SoldierUseCase = application.get()
    val soldiers: LiveData<List<SoldierModel>> = soldierUseCase.getAllSoldiers()

    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(Main + viewModelJob)

    fun contains(soldierModel: SoldierModel) = soldiers.value!!.contains(soldierModel)

    fun insert(name: String, serialNumber: String) =
            viewModelScope.launch { soldierUseCase.insertSoldier(name, serialNumber) }
    fun delete(deleteSoldierModel: SoldierModel) =
            viewModelScope.launch { soldierUseCase.deleteSoldiers(deleteSoldierModel) }


    companion object {
        class Factory(private val application: Application) : ViewModelProvider.NewInstanceFactory() {
            @Suppress("unchecked_cast")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(SoldierViewModel::class.java)) {
                    return SoldierViewModel(application) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }

    }

}