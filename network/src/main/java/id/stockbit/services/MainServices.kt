package id.stockbit.services

import id.stockbit.response.CoinResponse
import id.stockbit.response.PaginatedResponse
import id.stockbit.response.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MainServices {

    @GET("top/totaltoptiervolfull")
    suspend fun topCoins(
        @Header("Apikey") apiKey: String,
        @Query("tsym") currency: String,
        @Query("limit") limit: Int,
        @Query("page") page: Int
    ): PaginatedResponse<CoinResponse>

}