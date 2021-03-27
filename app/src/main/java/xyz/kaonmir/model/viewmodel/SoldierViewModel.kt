package xyz.kaonmir.model.viewmodel

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
import org.koin.core.component.KoinApiExtension
import xyz.kaonmir.model.model.Soldier
import xyz.kaonmir.model.repository.SoldierRepository

// todo(disposable checking)
// todo(coroutine)
// todo(fail to make viewModel to dependency injection

class SoldierViewModel(application: Application): AndroidViewModel(application) {
    private val soldierRepository: SoldierRepository = application.get()
    var soldiers: LiveData<List<Soldier>> = soldierRepository.getAll()

    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(Main + viewModelJob)

    fun insert(inputSoldier: Soldier) =
        viewModelScope.launch { soldierRepository.insert(inputSoldier) }


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