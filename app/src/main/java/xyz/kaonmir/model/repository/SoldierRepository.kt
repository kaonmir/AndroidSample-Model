package xyz.kaonmir.model.repository

import androidx.lifecycle.LiveData
import org.koin.core.component.KoinApiExtension
import xyz.kaonmir.model.dao.SoldierDao
import xyz.kaonmir.model.model.Soldier

@KoinApiExtension
class SoldierRepository(private val dataRepository: DataRepository) {
    companion object {
        @Volatile
        private var instance: SoldierRepository? = null

        fun getInstance(dataRepository: DataRepository): SoldierRepository {
            return instance ?: synchronized(this) {
                instance ?: SoldierRepository(dataRepository).also { instance = it }
            }
        }
    }

    private val soldierDao: SoldierDao
        get() = dataRepository.database.soldierDao()

    private val

}