package com.github.metnyov.addressbook.di

import android.app.Application
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.github.metnyov.addressbook.BuildConfig
import com.github.metnyov.addressbook.data.network.AddressBookApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.*
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val apiModule = DI.Module("api") {

    bind() from singleton {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    bind<HttpLoggingInterceptor>() with provider {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    bind<OkHttpClient.Builder>() with provider {
        val cacheSizeInMegabytes = 25L * 1024L * 1024L
        OkHttpClient.Builder()
            .readTimeout(25L, TimeUnit.SECONDS)
            .writeTimeout(15L, TimeUnit.SECONDS)
            .connectTimeout(15L, TimeUnit.SECONDS)
            .addNetworkInterceptor(StethoInterceptor())
            .cache(Cache(instance<Application>().cacheDir, cacheSizeInMegabytes))
            .apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(instance<HttpLoggingInterceptor>())
                }
            }
    }

    bind<OkHttpClient>() with singleton {
        instance<OkHttpClient.Builder>()
            .build()
    }

    // API
    bind<AddressBookApi>() with singleton {
        Retrofit.Builder()
            .baseUrl(BuildConfig.HOST)
            .addConverterFactory(MoshiConverterFactory.create(instance()))
            .client(instance())
            .build()
            .create(AddressBookApi::class.java)
    }
}