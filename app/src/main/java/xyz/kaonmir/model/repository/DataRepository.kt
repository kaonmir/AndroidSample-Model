package xyz.kaonmir.model.repository

import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

@KoinApiExtension
class DataRepository: KoinComponent {
    val database: AppDatabase = get()

    val soldierRepo: SoldierRepository = get()

}