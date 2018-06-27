package com.sshex.sunrise.di

import android.app.job.JobScheduler
import android.content.Context
import android.net.ConnectivityManager
import com.sshex.sunrise.common.location.AppLocationManager
import com.sshex.sunrise.common.location.LocationManager
import com.sshex.sunrise.extentions.CheckerPermissions
import com.sshex.sunrise.repository.SunriseRepository
import com.sshex.sunrise.repository.SunriseRepositoryImpl
import org.koin.dsl.module.applicationContext

val appModule = applicationContext {
    bean { get<Context>().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager }
    bean { get<Context>().getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler }
    bean { AppLocationManager(get(), get()) as LocationManager }
    bean { CheckerPermissions(get()) }
}