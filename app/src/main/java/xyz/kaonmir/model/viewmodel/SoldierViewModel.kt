package xyz.kaonmir.model.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.java.KoinJavaComponent.get
import xyz.kaonmir.model.model.Soldier
import xyz.kaonmir.model.repository.AppDatabase
import xyz.kaonmir.model.repository.DataRepository
import xyz.kaonmir.model.repository.SoldierRepository

// todo(disposable checking)

@KoinApiExtension
class SoldierViewModel(database: DataRepository): ViewModel() {
    private val soldiers: List<Soldier> by lazy {
        database.soldierRepo.getAll()
    }
}