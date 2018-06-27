package com.sshex.sunrise.ui.activity.sunrise

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.sshex.sunrise.R
import com.sshex.sunrise.base.activity.BaseLocationActivity
import com.sshex.sunrise.constant.LOCATION_PERMISSIONS_REQUEST
import com.sshex.sunrise.model.SunriseSunset
import com.sshex.sunrise.ui.activity.search.SearchCitiesActivity
import kotlinx.android.synthetic.main.activity_sunrise.*
import kotlinx.android.synthetic.main.sun_info.*


class SunriseActivity : BaseLocationActivity(), SunriseView {
    @InjectPresenter()
    lateinit var presenter: SunrisePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sunrise)

        chooseCity.setOnClickListener { startAllCitiesActivity() }

        if (savedInstanceState == null) {
            presenter.startSearchLocationIfPossible()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            LOCATION_PERMISSIONS_REQUEST -> {
                if (grantResults.size == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    startFindLocationInfo()
                } else {
                    Toast.makeText(this, getString(R.string.common_permissions_not_found), Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }

    override fun showLocationErrorMessage(messageCode: Int) {
        super.showLocationErrorMessage(messageCode)
        setUnavailableText();
    }

    override fun setUnavailableText() {
        currentCityName.text = getString(R.string.common_not_available)
    }

    override fun showPermissionsErrorMessage() {
        ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), LOCATION_PERMISSIONS_REQUEST)
    }

    override fun setCurrentCity(cityName: String) {
        currentCityName.text = cityName
    }

    override fun showSunriseSunsetInfo(sunriseSunset: SunriseSunset) {
        sunset.text = StringBuilder(getString(R.string.common_sunset)).append(": ").append(sunriseSunset.sunsetTime)
        sunrise.text = StringBuilder(getString(R.string.common_sunrise)).append(": ").append(sunriseSunset.sunriseTime)
    }


    private fun startAllCitiesActivity() {
        startActivity(Intent(this, SearchCitiesActivity::class.java))
    }

    private fun startFindLocationInfo() {
        presenter.startSearchLocationIfPossible()
    }
}