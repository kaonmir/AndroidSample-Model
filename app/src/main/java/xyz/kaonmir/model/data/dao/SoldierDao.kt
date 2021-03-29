package xyz.kaonmir.model.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import xyz.kaonmir.model.data.model.Soldier

@Dao
interface SoldierDao: BaseDao<Soldier> {
    @Query("SELECT * FROM soldier")
    fun getAll(): LiveData<List<Soldier>>

    @Query("SELECT * FROM soldier WHERE soldier_id LIKE :year || '-%'")
    fun getSoldiersByYear(year: Int): LiveData<List<Soldier>>

    @Query("SELECT * FROM soldier WHERE soldier_id = :serialNumber")
    fun getSoldierBySerialNumber(serialNumber: Int): Soldier
}

// todo(should express the "relation" of each entities - 1:N, N:M)
// todo(transaction annotation)