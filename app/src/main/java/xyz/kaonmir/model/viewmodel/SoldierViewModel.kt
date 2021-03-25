package xyz.kaonmir.model.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import xyz.kaonmir.model.model.Soldier
import xyz.kaonmir.model.repository.AppDatabase
import xyz.kaonmir.model.repository.SoldierRepository

// todo(disposable checking)

class SoldierViewModel(app: Application): AndroidViewModel(app) {
    private val repository: SoldierRepository
    private val soldiers:LiveData<List<Soldier>>

    init {
        val soldierDao = AppDatabase.getInstance(app).soldierDao()
        repository = SoldierRepository(soldierDao)
        soldiers = soldierDao.getAll()
    }
}