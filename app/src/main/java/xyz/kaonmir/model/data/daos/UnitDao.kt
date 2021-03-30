package xyz.kaonmir.model.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import xyz.kaonmir.model.data.entities.UnitModel

@Dao
interface UnitDao: BaseDao<UnitModel> {
    @Query("SELECT * FROM unit")
    fun getAll(): LiveData<List<UnitModel>>
}
