package com.jueggs.utils.base

import android.arch.lifecycle.LifecycleOwner
import android.support.annotation.StringRes
import android.widget.Toast

interface BaseView : LifecycleOwner {
    fun showLongToast(@StringRes resId: Int, vararg formatArgs: Any): Toast = TODO("not implemented")
    fun showLongToast(msg: String): Toast = TODO("not implemented")
    fun finishView() {
        TODO("not implemented")
    }

    fun finishViewAfterTransition() {
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

    fun hideSoftKeyboard(): Boolean {
        TODO("not implemented")
    }
}