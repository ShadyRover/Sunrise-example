package com.sshex.sunrise.ui.activity.sunrise

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.sshex.sunrise.base.activity.BaseLocationView

interface SunriseView : BaseLocationView {
    fun setCurrentCity(cityName: String)
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setUnavailableText()
}