package id.stockbit.app.di.module


import id.stockbit.app.ui.login.LoginViewModel
import id.stockbit.app.ui.main.MainViewModel
import id.stockbit.app.ui.main.menu.WatchlistViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel() }
    viewModel { MainViewModel() }
    viewModel { WatchlistViewModel(get()) }
}