package xyz.kaonmir.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import xyz.kaonmir.model.dao.SoldierDao
import xyz.kaonmir.model.model.Soldier

@Database(entities = [Soldier::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun soldierDao(): SoldierDao

    companion object {
        private const val DB_NAME = "room-db"
        private val instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context)
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME)
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                    }
                }).build()
        }
    }
}