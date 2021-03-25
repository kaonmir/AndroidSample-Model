package xyz.kaonmir.model.repository

import androidx.lifecycle.LiveData
import org.koin.core.component.KoinApiExtension
import xyz.kaonmir.model.dao.SoldierDao
import xyz.kaonmir.model.model.Soldier

class SoldierRepository(private val appDatabase: AppDatabase) {

    private val soldierDao: SoldierDao = appDatabase.soldierDao()

    fun getAll() = soldierDao.getAll()

}