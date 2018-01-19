package com.jueggs.utils.base

import android.support.annotation.StringRes
import android.widget.Toast

interface BaseView {
    fun longToast(@StringRes resId: Int): Toast
    fun longToast(msg: String): Toast
}