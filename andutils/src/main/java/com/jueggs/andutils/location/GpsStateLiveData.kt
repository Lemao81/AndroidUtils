package com.jueggs.andutils.location

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.location.LocationManager
import androidx.lifecycle.LiveData
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