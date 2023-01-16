package com.example.unlimint.app

import android.app.Application
import com.example.unlimint.networkModule
import com.example.unlimint.viewModelModule
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin



class MyApp : Application() {


    override fun onCreate() {
        super.onCreate()
        startKoin {
            HttpLoggingInterceptor.Logger
            androidContext(androidContext = this@MyApp)
modules(
                listOf(networkModule, viewModelModule)
            )
        }
    }
}