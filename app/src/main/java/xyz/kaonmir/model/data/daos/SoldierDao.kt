package xyz.kaonmir.model.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import xyz.kaonmir.model.data.entities.SoldierModel

@Dao
interface SoldierDao: BaseDao<SoldierModel> {
    @Query("SELECT * FROM soldier")
    fun getAll(): LiveData<List<SoldierModel>>

    @Query("SELECT * FROM soldier WHERE soldier_id LIKE :year || '-%'")
    fun getSoldiersByYear(year: Int): LiveData<List<SoldierModel>>

    @Query("SELECT * FROM soldier WHERE soldier_id = :serialNumber")
    suspend fun getSoldierBySerialNumber(serialNumber: String): SoldierModel?
}

// todo(should express the "relation" of each entities - 1:N, N:M)
// todo(transaction annotation)