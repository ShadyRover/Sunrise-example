package com.sshex.sunrise.extentions

import com.sshex.sunrise.model.SunriseSunset
import com.sshex.sunrise.repository.dto.SunriseSunsetResponse
import com.sshex.sunrise.repository.dto.SunriseSunsetResultResponse
import org.koin.KoinContext
import org.koin.standalone.StandAloneContext

inline fun <reified T> inject() = lazy { (StandAloneContext.koinContext as KoinContext).get<T>() }

fun createSunriseSunsetFromServerResponse(resultResponse: SunriseSunsetResponse): SunriseSunset =
        SunriseSunset().apply {
            sunsetTime = getTimeFromDate(resultResponse.sunset)
            sunsetTime = getTimeFromDate(resultResponse.sunset)
            sunriseTime = getTimeFromDate(resultResponse.sunrise)
            dayLength = getTimeFromDate(resultResponse.dayLength)
        }

private fun getTimeFromDate(date: String) =
        date.split("T").getOrElse(1) { "" }.split("+").getOrElse(0) { "" }