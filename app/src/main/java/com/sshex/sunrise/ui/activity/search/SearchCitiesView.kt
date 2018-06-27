package com.sshex.sunrise.ui.activity.search

import com.sshex.sunrise.base.activity.BaseLocationView

interface SearchCitiesView : BaseLocationView{
    fun showCityName(cityName: CharSequence)
}
