package com.sshex.sunrise.ui.activity.search

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.google.android.gms.location.places.Place
import com.sshex.sunrise.common.rx.SchedulerProvider
import com.sshex.sunrise.constant.LOAD_INFO_BY_COORDS_FAILED
import com.sshex.sunrise.extentions.createSunriseSunsetFromServerResponse
import com.sshex.sunrise.extentions.inject
import com.sshex.sunrise.extentions.with
import com.sshex.sunrise.model.SunriseSunset
import com.sshex.sunrise.repository.SunriseRepository

@InjectViewState
class SearchCitiesPresenter : MvpPresenter<SearchCitiesView>() {
    private val sunriseSunsetManager: SunriseRepository by inject()
    private val schedulerProvider: SchedulerProvider by inject()
    private lateinit var lastPlace: Place


    private fun loadInfoByLastPlace() {
        sunriseSunsetManager.requestSunriseInfo(lastPlace.latLng.latitude, lastPlace.latLng.longitude)
                .with(schedulerProvider)
                .doOnError { viewState.showLocationErrorMessage(LOAD_INFO_BY_COORDS_FAILED) }
                .map<SunriseSunset> { response -> createSunriseSunsetFromServerResponse(response.result) }
                .subscribe { sunriseSunset -> viewState.showSunriseSunsetInfo(sunriseSunset) }
    }

    fun searchPlaceInfo(place: Place) {
        lastPlace = place
        viewState.showCityName(lastPlace.name)
        loadInfoByLastPlace()
    }
}