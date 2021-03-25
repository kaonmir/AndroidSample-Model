package xyz.kaonmir.model

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module
import xyz.kaonmir.model.repository.AppDatabase

class ModelApp: Application() {
    override fun onCreate() {
        super.onCreate()

        val databaseModule = module {
            single { AppDatabase.getInstance(applicationContext) }

        }


        startKoin {
            androidLogger()                           // Use Koin logger instead of Android logger
            androidContext(this@ModelApp)// Use this context
            androidFileProperties()     // get properties from "assets/koin.properties"

            modules(listOf(
                    databaseModule
            )) // Module list
        }
    }
}