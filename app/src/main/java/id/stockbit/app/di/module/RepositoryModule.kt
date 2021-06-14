package id.stockbit.app.di.module

import id.stockbit.app.repository.MainRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory { MainRepository(get(), get(), get()) }
}