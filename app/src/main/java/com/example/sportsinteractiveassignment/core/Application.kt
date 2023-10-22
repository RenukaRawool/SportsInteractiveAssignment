package com.example.sportsinteractiveassignment.core

import android.app.Application
import com.example.sportsinteractiveassignment.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin(){
        startKoin{
            modules(appModule)
            androidContext(this@MainApplication)
        }
    }
}
