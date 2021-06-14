package id.stockbit.app.di.source

import androidx.room.Room
import id.stockbit.database.UserDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localDataSourceModule = module {
    factory { Room.databaseBuilder(androidContext(), UserDatabase::class.java, "user.db").build() }
}