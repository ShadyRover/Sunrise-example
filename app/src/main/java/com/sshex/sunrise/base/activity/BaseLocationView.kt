package com.sshex.sunrise.base.activity

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.sshex.sunrise.model.SunriseSunset

@StateStrategyType(OneExecutionStateStrategy::class)
interface BaseLocationView : MvpView {
    fun showPermissionsErrorMessage()

    fun showLocationErrorMessage(messageCode: Int)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showSunriseSunsetInfo(sunriseSunset: SunriseSunset)
}