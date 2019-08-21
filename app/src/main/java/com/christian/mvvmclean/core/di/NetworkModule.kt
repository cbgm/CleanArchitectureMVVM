package com.christian.mvvmclean.core.di

import android.content.Context
import com.christian.mvvmclean.core.utils.BASE_URL
import com.christian.mvvmclean.core.utils.FakeInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


val networkModule = module {

    single<Retrofit>(named("retrofit")) {

        Retrofit.Builder()
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(createOkHttpClient(get()))
            .build()
    }
}


private fun createOkHttpClient(context: Context): OkHttpClient {
    val cacheSize = (24 * 1024 * 1024).toLong()
    val cache = Cache(context.cacheDir, cacheSize)
    return OkHttpClient.Builder()
        .cache(cache)
        //.addInterceptor(FakeInterceptor())
        .build()
}


inline fun <reified T> createWebService(retrofit: Retrofit): T {
    return retrofit.create(T::class.java)
}