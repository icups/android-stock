package id.stockbit.app.di.source

import id.stockbit.services.MainServices
import org.koin.dsl.module
import retrofit2.Retrofit

val remoteDataSource = module {
    factory { provideMainServices(get()) }
}

fun provideMainServices(retrofit: Retrofit): MainServices {
    return retrofit.create(MainServices::class.java)
}