package com.sshex.sunrise

import android.app.Application
import com.sshex.sunrise.di.appModule
import com.sshex.sunrise.di.networkModule
import com.sshex.sunrise.di.rxModule
import org.koin.android.ext.android.startKoin

open class SunriseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule, networkModule, rxModule))
    }
}