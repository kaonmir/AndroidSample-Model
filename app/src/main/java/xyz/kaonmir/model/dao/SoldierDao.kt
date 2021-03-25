package xyz.kaonmir.model.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import xyz.kaonmir.model.model.Soldier
import java.sql.Array

@Dao
interface SoldierDao: BaseDao<Soldier> {
    @Query("SELECT * FROM soldier")
    fun getAll(): List<Soldier>

    @Query("SELECT * FROM soldier WHERE serialNumber LIKE ':year-%'")
    fun getSoldierBySerialNumber(year: Int): List<Soldier>

    @Query("SELECT * FROM soldier WHERE serialNumber")
    fun getSoldiersByYear(year: Int): Soldier
}

// todo(should express the "relation" of each entities - 1:N, N:M)
// todo(transaction annotation)
// todo(liveData)