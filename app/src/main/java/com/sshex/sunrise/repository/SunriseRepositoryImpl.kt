package com.sshex.sunrise.repository

import com.sshex.sunrise.network.SunriseSunsetApi
import com.sshex.sunrise.repository.dto.SunriseSunsetResultResponse
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class SunriseRepositoryImpl : SunriseRepository {
    companion object {
        const val BASE_API_URL = "https://api.sunrise-sunset.org/"
    }

    private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    private val sunriseSunsetApi = retrofit.create(SunriseSunsetApi::class.java)


    override fun requestSunriseInfo(latitude: Double, longitude: Double): Single<SunriseSunsetResultResponse> =
            sunriseSunsetApi.requestSunriseInfo(latitude, longitude)
}