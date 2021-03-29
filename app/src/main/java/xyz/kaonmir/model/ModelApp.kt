package xyz.kaonmir.model

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinApiExtension
import org.koin.core.context.startKoin
import org.koin.dsl.module
import xyz.kaonmir.model.data.repository.AppDatabase
import xyz.kaonmir.model.data.repository.SoldierRepository

class ModelApp: Application() {
    @KoinApiExtension
    override fun onCreate() {
        super.onCreate()

        val databaseModule = module {
            single { AppDatabase.getInstance(applicationContext) }
            single { SoldierRepository(get()) }
        }

        startKoin {
            androidLogger()                           // Use Koin logger instead of Android logger
            androidContext(this@ModelApp)// Use this context
            androidFileProperties()                   // get properties from "assets/koin.properties"

            modules(databaseModule)
        }
    }
}