package com.jueggs.andutils.helper

import android.content.res.Resources

object ResourceIdentifier {
    fun getStatusBarHeight(resources: Resources): Int {
        val statusBarHeightResId = resources.getIdentifier(STATUS_BAR_HEIGHT, Type.DIMEN, Package.ANDROID)
        return if (statusBarHeightResId > 0) resources.getDimensionPixelSize(statusBarHeightResId) else 0
    }

    object Type {
        const val DIMEN = "dimen"
    }

    object Package {
        const val ANDROID = "android"
    }

    const val STATUS_BAR_HEIGHT: String = "status_bar_height"
}