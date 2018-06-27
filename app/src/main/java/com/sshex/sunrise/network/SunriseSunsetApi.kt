package com.sshex.sunrise.network

import com.sshex.sunrise.repository.dto.SunriseSunsetResultResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SunriseSunsetApi {
    @GET("json")
    fun requestSunriseInfo(@Query("lat") latitude: Double, @Query("lng") longitude: Double,
                           @Query("formatted") formattedType: Int = 0): Single<SunriseSunsetResultResponse>
}