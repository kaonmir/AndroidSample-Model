package xyz.kaonmir.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import xyz.kaonmir.model.model.Soldier

// todo(suspend 쓰는 것의 기준)
@Dao
interface BaseDao<T> {
    @Insert
    suspend fun insert(obj: T)

    @Insert
    suspend fun insert(vararg obj: T)

    @Update
    suspend fun update(obj: T)

    @Delete
    suspend fun delete(obj: T)
}