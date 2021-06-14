package id.stockbit.app.di.module

import id.stockbit.app.shared.AppPreferences
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    factory { AppPreferences(androidContext()) }
}