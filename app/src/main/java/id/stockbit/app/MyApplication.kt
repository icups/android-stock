package id.stockbit.app

import androidx.multidex.MultiDexApplication
import id.stockbit.app.di.module.appModule
import id.stockbit.app.di.module.networkModule
import id.stockbit.app.di.module.repositoryModule
import id.stockbit.app.di.module.viewModelModule
import id.stockbit.app.di.source.localDataSourceModule
import id.stockbit.app.di.source.remoteDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class MyApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(appModules())
        }
    }

    private fun appModules(): List<Module> {
        return listOf(
            appModule,
            networkModule,
            localDataSourceModule,
            remoteDataSource,
            repositoryModule,
            viewModelModule
        )
    }

}