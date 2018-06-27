package com.sshex.sunrise.repository.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SunriseSunsetResultResponse(@SerializedName("results") @Expose var result: SunriseSunsetResponse)