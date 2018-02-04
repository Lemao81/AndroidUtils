package com.jueggs.utils.base

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import android.support.annotation.*
import android.widget.Toast
import com.jueggs.utils.extension.getStringArray
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

    fun showAlertDialog(context: Context, @StringRes titleResId: Int?, @StringRes messageResId: Int, confirmAction: () -> Unit, denyAction: () -> Unit = {}) =
            showAlertDialog(context, if (titleResId != null) context.getString(titleResId) else null, context.getString(messageResId), confirmAction, denyAction)

    fun showAlertDialog(context: Context, title: CharSequence?, message: CharSequence, confirmAction: () -> Unit, denyAction: () -> Unit = {}) {
        context.alert(message, title) {
            yesButton { confirmAction() }
            noButton { denyAction() }
        }.show()
    }

    fun showSelection(context: Context, @StringRes titleResId: Int?, @ArrayRes itemsResId: Int, onSelect: (Int) -> Unit) =
            showSelection(context, titleResId, context.getStringArray(itemsResId).asList(), onSelect)

    fun showSelection(context: Context, @StringRes titleResId: Int?, items: List<CharSequence>, onSelect: (Int) -> Unit) =
            showSelection(context, if (titleResId != null) context.getString(titleResId) else null, items, onSelect)

    fun showSelection(context: Context, title: CharSequence?, items: List<CharSequence>, onSelect: (Int) -> Unit) = context.selector(title, items, { _, index -> onSelect(index) })

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