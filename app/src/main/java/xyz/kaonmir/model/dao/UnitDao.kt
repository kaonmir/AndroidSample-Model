package xyz.kaonmir.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import xyz.kaonmir.model.model.Soldier
import xyz.kaonmir.model.model.Unit

@Dao
interface UnitDao: BaseDao<Unit> {
    @Query("SELECT * FROM unit")
    fun getAll(): LiveData<List<Unit>>

    @Query("SELECT * FROM unit WHERE unit_id = :id")
    fun getUnitById(id: Int): LiveData<Unit>
}
