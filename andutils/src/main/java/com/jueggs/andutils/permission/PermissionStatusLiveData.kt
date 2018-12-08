package com.jueggs.andutils.permission

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LiveData

class PermissionStatusLiveData(context: Context, private val permission: String) : LiveData<PermissionStatus>() {
    private val appContext = context.applicationContext

    override fun onActive() {
        val isGranted = ActivityCompat.checkSelfPermission(appContext, permission) == PackageManager.PERMISSION_GRANTED
        postValue(if (isGranted) Granted else Denied)
    }
}