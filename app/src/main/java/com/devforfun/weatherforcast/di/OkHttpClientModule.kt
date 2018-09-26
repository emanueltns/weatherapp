package com.devforfun.weatherforcast.di

import android.content.Context
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.io.File
import javax.inject.Singleton

@Module(includes = [ContextModule::class])
class OkHttpClientModule {

    @WeatherApplicationScope
    @Provides
    fun okHttpClient(cache: Cache,
                     httpLoggingInterceptor: HttpLoggingInterceptor) : OkHttpClient {
        return OkHttpClient()
                .newBuilder()
                .cache(cache)
                .addInterceptor(httpLoggingInterceptor).build()
    }

    @WeatherApplicationScope
    @Provides
    fun cache(cacheFile : File) : Cache {
        return Cache(cacheFile, 10 * 1000 * 1000) //10MB
    }

    @WeatherApplicationScope
    @Provides
    fun file(context: Context) : File {
        val file = File(context.cacheDir, "HttpCache")
        file.mkdirs()
        return file
    }

    @WeatherApplicationScope
    @Provides
    fun httpLogginInterceptor() : HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            message -> Timber.d(message);
        });
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }
}