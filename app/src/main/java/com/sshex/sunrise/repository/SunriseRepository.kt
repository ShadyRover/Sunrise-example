package com.sshex.sunrise.repository

import com.sshex.sunrise.repository.dto.SunriseSunsetResultResponse
import io.reactivex.Single

interface SunriseRepository {
    fun requestSunriseInfo(latitude: Double, longitude: Double): Single<SunriseSunsetResultResponse>
}
