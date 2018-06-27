package com.sshex.sunrise.ui.activity.search

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.google.android.gms.location.places.AutocompleteFilter
import com.google.android.gms.location.places.ui.PlaceAutocomplete
import com.sshex.sunrise.R
import com.sshex.sunrise.base.activity.BaseLocationActivity
import com.sshex.sunrise.constant.SEARCH_CITY_ACTIVITY_REQUEST_CODE
import com.sshex.sunrise.model.SunriseSunset
import kotlinx.android.synthetic.main.sun_info.*


class SearchCitiesActivity : BaseLocationActivity(), SearchCitiesView {
    @InjectPresenter()
    lateinit var presenter: SearchCitiesPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_citites)
        if (savedInstanceState == null)
            startSearchCity()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == SEARCH_CITY_ACTIVITY_REQUEST_CODE) {
            when (resultCode) {
                Activity.RESULT_OK -> presenter.searchPlaceInfo(PlaceAutocomplete.getPlace(this, data))
                PlaceAutocomplete.RESULT_ERROR -> showErrorOnSearchPlace(data)
            }
        }
    }

    override fun showSunriseSunsetInfo(sunriseSunset: SunriseSunset) {
        sunset.text = StringBuilder(getString(R.string.common_sunset)).append(": ").append(sunriseSunset.sunsetTime)
        sunrise.text = StringBuilder(getString(R.string.common_sunrise)).append(": ").append(sunriseSunset.sunriseTime)
    }

    override fun showPermissionsErrorMessage() {
        Toast.makeText(this, R.string.common_permissions_not_found, Toast.LENGTH_LONG).show()
    }


    override fun showCityName(cityName: CharSequence) {
        currentCityName.text = cityName
    }


    private fun showErrorOnSearchPlace(data: Intent) {
        Toast.makeText(this, PlaceAutocomplete.getStatus(this, data).statusMessage, Toast.LENGTH_LONG).show()
    }

    private fun startSearchCity() {
        startActivityForResult(createIntentFilterCities(), SEARCH_CITY_ACTIVITY_REQUEST_CODE)
    }

    private fun createIntentFilterCities() =
            PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY).setFilter(createAutocompleteCitiesFilter()).build(this)

    private fun createAutocompleteCitiesFilter(): AutocompleteFilter =
            AutocompleteFilter.Builder().setTypeFilter(AutocompleteFilter.TYPE_FILTER_CITIES).build()
}

