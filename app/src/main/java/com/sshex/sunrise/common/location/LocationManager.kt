package com.sshex.sunrise.common.location

import android.Manifest
import android.location.Location
import android.support.annotation.RequiresPermission
import io.reactivex.Single

interface LocationManager {
    @RequiresPermission(anyOf = [(Manifest.permission.ACCESS_COARSE_LOCATION), (Manifest.permission.ACCESS_FINE_LOCATION)])
    fun startFindCoordinates(): Single<Location>
}