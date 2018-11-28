package com.jueggs.andutils.location

import android.arch.lifecycle.LiveData
import android.content.*
import android.location.LocationManager
import com.jueggs.andutils.state.Disabled
import com.jueggs.andutils.state.Enabled
import com.jueggs.andutils.state.ProviderState
import org.jetbrains.anko.locationManager

class GpsStateLiveData(context: Context) : LiveData<ProviderState>() {
    private val appContext = context.applicationContext
    private val gpsReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) = checkGpsStatus()
    }

    override fun onActive() {
        appContext.registerReceiver(gpsReceiver, IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION))
        checkGpsStatus()
    }

    override fun onInactive() = appContext.unregisterReceiver(gpsReceiver)

    private fun isGpsEnabled() = appContext.locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

    private fun checkGpsStatus() = postValue(if (isGpsEnabled()) Enabled else Disabled)
}