package xyz.kaonmir.koin.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import xyz.kaonmir.koin.model.Soldier

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