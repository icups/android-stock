package id.stockbit.app.di.module

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import id.stockbit.app.BuildConfig
import id.stockbit.ext.common.applyIf
import id.stockbit.interceptor.AppInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val REQUEST_TIMEOUT = 30L
private const val CONTENT_MAX_LENGTH = 250000L

val networkModule = module {
    factory { provideChuckInterceptor(androidContext()) }
    factory { provideConverterFactory() }
    factory { provideClient(get()) }
    factory { provideRetrofit(get(), get()) }
}

fun provideChuckInterceptor(context: Context): ChuckerInterceptor {
    return ChuckerInterceptor.Builder(context)
        .collector(ChuckerCollector(context))
        .maxContentLength(CONTENT_MAX_LENGTH)
        .redactHeaders(emptySet())
        .alwaysReadResponseBody(false)
        .build()
}

fun provideConverterFactory(): Converter.Factory {
    val builder = GsonBuilder()
        .setLenient()
        .create()

    return GsonConverterFactory.create(builder)
}

fun provideClient(chuckInterceptor: ChuckerInterceptor): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    return OkHttpClient.Builder()
        .addInterceptor(AppInterceptor())
        .addInterceptor(httpLoggingInterceptor)
        .applyIf(BuildConfig.DEBUG) { addInterceptor(chuckInterceptor) }
        .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
        .build()
}

fun provideRetrofit(httpClient: OkHttpClient, converter: Converter.Factory): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.URL_API)
        .client(httpClient)
        .addConverterFactory(converter)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
}