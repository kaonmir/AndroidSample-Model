package xyz.kaonmir.model.data.repository

import xyz.kaonmir.model.data.dao.SoldierDao
import xyz.kaonmir.model.data.model.Soldier

// todo(cache)

class SoldierRepository(private val appDatabase: AppDatabase) {

    private val soldierDao: SoldierDao = appDatabase.soldierDao()

    suspend fun insert(inputSoldier: Soldier) = soldierDao.insert(inputSoldier)
    suspend fun update(inputSoldier: Soldier) = soldierDao.update(inputSoldier)
    suspend fun delete(inputSoldier: Soldier) = soldierDao.delete(inputSoldier)

    fun getAll() = soldierDao.getAll()
    fun getSoldiersByYear(year: Int) = soldierDao.getSoldiersByYear(year)
    fun getSoldierBySerialNumber(serialNumber: Int) = soldierDao.getSoldierBySerialNumber(serialNumber)
}