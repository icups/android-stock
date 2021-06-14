package id.stockbit.app.repository

import id.stockbit.app.BuildConfig
import id.stockbit.app.shared.AppPreferences
import id.stockbit.constant.Page
import id.stockbit.database.UserDatabase
import id.stockbit.response.CoinResponse
import id.stockbit.response.PaginatedResponse
import id.stockbit.services.MainServices

class MainRepository(
    private val remoteDataSource: MainServices,
    private val localDataSource: UserDatabase,
    private val appPreferences: AppPreferences
) {

    suspend fun topCoins(page: Int): PaginatedResponse<CoinResponse> {
        return remoteDataSource.topCoins(BuildConfig.KEY_API, Page.DEFAULT_CURRENCY, Page.LIMIT, page)
    }

}