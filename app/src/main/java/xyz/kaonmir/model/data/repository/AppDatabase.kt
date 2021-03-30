package xyz.kaonmir.model.data.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import xyz.kaonmir.model.data.dao.SoldierDao
import xyz.kaonmir.model.data.model.Soldier

@Database(entities = [
    Soldier::class
                     ], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun soldierDao(): SoldierDao
//    abstract fun unitDao(): UnitDao


    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        private const val DB_NAME = "room-db"

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context)
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context.applicationContext,
                    AppDatabase::class.java, DB_NAME).build().also { INSTANCE = it }
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}