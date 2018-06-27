package com.sshex.sunrise.di

import com.sshex.sunrise.repository.SunriseRepository
import com.sshex.sunrise.repository.SunriseRepositoryImpl
import org.koin.dsl.module.applicationContext

val networkModule = applicationContext {
    bean { SunriseRepositoryImpl() as SunriseRepository }
}