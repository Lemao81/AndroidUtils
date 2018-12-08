package com.jueggs.andutils.extension

import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.isFragmentVisible(@IdRes resId: Int) = supportFragmentManager.findFragmentById(resId) != null

fun AppCompatActivity.setHomeBackIcon(@DrawableRes resId: Int) = supportActionBar?.setHomeAsUpIndicator(resId)

fun AppCompatActivity.setPaddingToStatusBarHeight(view: View) {
    val resId = resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resId <= 0) return
    view.setPadding(0, resources.getDimensionPixelSize(resId), 0, 0)
}