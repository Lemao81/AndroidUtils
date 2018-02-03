package com.jueggs.utils.base

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import android.support.annotation.StringRes
import android.widget.Toast
import org.jetbrains.anko.*

interface BaseView : LifecycleOwner {
    fun showLongToast(@StringRes resId: Int, vararg formatArgs: Any): Toast = TODO("not implemented")
    fun showLongToast(msg: String): Toast = TODO("not implemented")
    fun finishView() {
        TODO("not implemented")
    }

    fun finishViewAfterTransition() {
        TODO("not implemented")
    }

    fun showAlertDialog(context: Context, @StringRes titleResId: Int, @StringRes messageResId: Int, confirmAction: () -> Unit, denyAction: () -> Unit = {}) =
            showAlertDialog(context, context.getString(titleResId), context.getString(messageResId), confirmAction, denyAction)

    fun showAlertDialog(context: Context, title: String, message: String, confirmAction: () -> Unit, denyAction: () -> Unit = {}) {
        context.alert(message, title) {
            yesButton { confirmAction() }
            noButton { denyAction() }
        }.show()
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