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
import xyz.kaonmir.model.domain.repositories.SoldierRepository

// todo(disposable checking)
// todo(fail to make viewModel to dependency injection
// todo(decouple between viewModel and repository directly)

class SoldierViewModel(application: Application): AndroidViewModel(application) {
    private val soldierRepository: SoldierRepository = application.get()
    val soldiers: LiveData<List<SoldierModel>> = soldierRepository.getAll()

    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(Main + viewModelJob)

    fun contains(soldierModel: SoldierModel) = soldiers.value!!.contains(soldierModel)

    fun insert(inputSoldierModel: SoldierModel) =
            viewModelScope.launch { soldierRepository.insert(inputSoldierModel) }
    fun delete(deleteSoldierModel: SoldierModel) =
            viewModelScope.launch { soldierRepository.delete(deleteSoldierModel) }


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