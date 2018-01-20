package com.jueggs.utils.base

import android.arch.lifecycle.LifecycleOwner
import android.support.annotation.StringRes
import android.widget.Toast

interface BaseView : LifecycleOwner {
    fun longToast(@StringRes resId: Int): Toast
    fun longToast(msg: String): Toast
    fun finish()
}