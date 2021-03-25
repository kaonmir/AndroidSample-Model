package xyz.kaonmir.model

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.component.KoinApiExtension
import org.koin.core.context.startKoin
import org.koin.dsl.module
import xyz.kaonmir.model.repository.AppDatabase
import xyz.kaonmir.model.repository.DataRepository
import xyz.kaonmir.model.repository.SoldierRepository
import xyz.kaonmir.model.viewmodel.SoldierViewModel

class ModelApp: Application() {
    @KoinApiExtension
    override fun onCreate() {
        super.onCreate()

        val databaseModule = module {
            single {
                AppDatabase.getInstance(applicationContext)
                SoldierRepository(get())
            }
        }

        val viewModelModule = module {
            viewModel {
                SoldierViewModel(get())
            }
        }


        startKoin {
            androidLogger()                           // Use Koin logger instead of Android logger
            androidContext(this@ModelApp)// Use this context
            androidFileProperties()     // get properties from "assets/koin.properties"

            modules(databaseModule)
            modules(viewModelModule)
        }
    }
}