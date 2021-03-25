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
interface SoldierDao {
    @Query("SELECT * FROM soldier")
    suspend fun getAll(): List<Soldier>

    @Query("SELECT * FROM soldier WHERE serialNumber LIKE ':year-%'")
    fun getAllByYear(year: Int): List<Soldier>

    @Insert
    suspend fun insertAll(vararg soldiers: Soldier)

    @Delete
    suspend fun delete(soldier: Soldier)
}

// todo(should express the "relation" of each entities - 1:N, N:M)
// todo(transaction annotation)