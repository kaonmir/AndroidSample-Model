package xyz.kaonmir.koin

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module
import xyz.kaonmir.koin.database.AppDatabase

class KoinApp: Application() {
    override fun onCreate() {
        super.onCreate()

        val databaseModule = module {
            single { AppDatabase.getInstance(applicationContext) } // singleton

            // get(): Inject necessary component
            // named()
            // bind: additional binding with the component
            // getProperty()
        }


        startKoin {
            androidLogger()                           // Use Koin logger instead of Android logger
            androidContext(this@KoinApp)// Use this context
            androidFileProperties()     // get properties from "assets/koin.properties"

            modules(databaseModule)          // Module list
        }
    }
}