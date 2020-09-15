package com.khs.dagger2

import timber.log.Timber
import javax.inject.Inject

class NickelCadmiumBattery @Inject constructor(): Battery {
    override fun getPower() {
        Timber.d("Power from NickelCadmiumBattery")
    }
}