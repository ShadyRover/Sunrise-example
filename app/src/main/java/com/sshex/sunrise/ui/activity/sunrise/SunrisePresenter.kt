package com.sshex.sunrise.ui.activity.sunrise

import android.content.Context
import android.location.Geocoder
import android.location.Location
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.sshex.sunrise.common.location.LocationManager
import com.sshex.sunrise.constant.LOAD_COORDS_FAILED
import com.sshex.sunrise.constant.LOAD_INFO_BY_COORDS_FAILED
import com.sshex.sunrise.extentions.CheckerPermissions
import com.sshex.sunrise.extentions.createSunriseSunsetFromServerResponse
import com.sshex.sunrise.extentions.inject
import com.sshex.sunrise.model.SunriseSunset
import com.sshex.sunrise.repository.SunriseRepository
import com.sshex.sunrise.repository.dto.SunriseSunsetResultResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

@InjectViewState
class SunrisePresenter : MvpPresenter<SunriseView>() {
    private val checkerPermissions: CheckerPermissions by inject()
    private val locationManager: LocationManager by inject()
    private val sunriseRepository: SunriseRepository by inject()
    private val applicationContext: Context by inject()

    fun startSearchLocationIfPossible() {
        viewState.setUnavailableText();
        if (checkerPermissions.isAnyLocationAvailable()) {
            startFindCoordinates()
        } else {
            viewState.showPermissionsErrorMessage()
        }
    }

    private fun startFindCoordinates() {
        locationManager.startFindCoordinates()
                .doOnSuccess { location -> viewState.setCurrentCity(getCityFromLocation(location)) }
                .doOnError { viewState.showLocationErrorMessage(LOAD_COORDS_FAILED) }
                .flatMap<SunriseSunsetResultResponse> {
                    sunriseRepository.requestSunriseInfo(it.latitude, it.longitude)
                            .subscribeOn(Schedulers.io())
                }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { viewState.showLocationErrorMessage(LOAD_INFO_BY_COORDS_FAILED) }
                .map<SunriseSunset> { response -> createSunriseSunsetFromServerResponse(response.result) }
                .subscribe { sunriseSunset -> viewState.showSunriseSunsetInfo(sunriseSunset) }
    }

    private fun getCityFromLocation(location: Location) =
            Geocoder(applicationContext, Locale.getDefault()).getFromLocation(location.latitude, location.longitude, 1)[0].locality
}