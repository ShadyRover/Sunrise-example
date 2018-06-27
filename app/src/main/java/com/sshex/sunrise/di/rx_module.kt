package com.sshex.sunrise.di

import com.sshex.sunrise.common.rx.ApplicationSchedulerProvider
import com.sshex.sunrise.common.rx.SchedulerProvider
import org.koin.dsl.module.applicationContext

val rxModule = applicationContext {
    // provided components
    bean { ApplicationSchedulerProvider() as SchedulerProvider }
}