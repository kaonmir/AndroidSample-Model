package xyz.kaonmir.model.data.repositories

import xyz.kaonmir.model.data.daos.SoldierDao
import xyz.kaonmir.model.data.entities.SoldierModel

// todo(cache)
// todo(combination of remote and local)
// use of repository? It has only meaning of gathering all model layers
// If we use both remote and local database, isn't it be meaningful?

class SoldierRepository(appDatabase: AppDatabase) {

    private val soldierDao: SoldierDao = appDatabase.soldierDao()

    suspend fun insert(inputSoldier: SoldierModel) = soldierDao.insert(inputSoldier)
    suspend fun update(inputSoldier: SoldierModel) = soldierDao.update(inputSoldier)
    suspend fun delete(inputSoldier: SoldierModel) = soldierDao.delete(inputSoldier)

    fun getAll() = soldierDao.getAll()
    fun getSoldiersByYear(year: Int) = soldierDao.getSoldiersByYear(year)
    suspend fun getSoldierBySerialNumber(serialNumber: String) = soldierDao.getSoldierBySerialNumber(serialNumber)
}