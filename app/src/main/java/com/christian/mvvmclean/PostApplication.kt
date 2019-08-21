package com.christian.mvvmclean

import android.app.Application
import com.christian.mvvmclean.core.di.appModule
import com.christian.mvvmclean.core.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PostApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@PostApplication)
            modules(listOf(appModule, networkModule))
        }
    }
}