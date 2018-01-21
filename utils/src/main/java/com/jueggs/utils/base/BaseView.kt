package com.jueggs.utils.base

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.support.annotation.StringRes
import android.widget.Toast

interface BaseView : LifecycleOwner {
    fun longToast(@StringRes resId: Int): Toast = TODO("not implemented")
    fun longToast(msg: String): Toast = TODO("not implemented")
    fun finish() {
        TODO("not implemented")
    }

    fun finishAfterTransitionCompat() {
        TODO("not implemented")
    }

    fun showAlertDialog(title: String, text: String, confirmAction: () -> Unit, denyAction: () -> Unit = {}) {
        TODO("not implemented")
    }

    fun showProgress() {
        TODO("not implemented")
    }

    fun hideProgress() {
        TODO("not implemented")
    }
}

interface LifecycleOwnerStub : LifecycleOwner {
    override fun getLifecycle(): Lifecycle {
        TODO("not implemented")
    }
}