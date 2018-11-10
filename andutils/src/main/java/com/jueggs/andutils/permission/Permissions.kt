package com.jueggs.andutils.permission

import android.content.Context
import com.nabinbhandari.android.permissions.PermissionHandler
import com.nabinbhandari.android.permissions.Permissions

object Permissions {
    fun check(context: Context, permission: String, grantedAction: () -> Unit) {
        Permissions.check(context, permission, null, object : PermissionHandler() {
            override fun onGranted() = grantedAction()
        })
    }
}