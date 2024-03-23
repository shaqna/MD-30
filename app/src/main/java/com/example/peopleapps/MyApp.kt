package com.example.peopleapps

import android.app.Application
import com.example.peopleapps.data.di.dataModule
import com.example.peopleapps.data.di.retrofit
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApp)
            androidLogger(Level.ERROR)

            modules(
                listOf(
                    retrofit,
                    dataModule
                )
            )
        }
    }
}