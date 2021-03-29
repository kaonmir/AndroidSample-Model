package xyz.kaonmir.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import xyz.kaonmir.model.model.Soldier

// todo(suspend 쓰는 것의 기준)
@Dao
interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(obj: T)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vararg obj: T)

    @Update
    suspend fun update(obj: T)

    @Delete
    suspend fun delete(obj: T)
}