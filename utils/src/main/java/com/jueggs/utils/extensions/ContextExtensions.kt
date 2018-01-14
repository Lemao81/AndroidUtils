package com.jueggs.utils.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.support.annotation.ArrayRes
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import com.jueggs.utils.createSharedElement
import org.jetbrains.anko.connectivityManager

fun Context.verticalLinearLayoutManager(): RecyclerView.LayoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
fun Context.horizontalLinearLayoutManager(): RecyclerView.LayoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
@SuppressLint("MissingPermission")
fun Context.isNetworkConnected(): Boolean = connectivityManager.activeNetworkInfo.isConnectedOrConnecting

fun Context.createSimpleSpinnerAdapter(@ArrayRes arrayResId: Int): ArrayAdapter<String> = createSimpleSpinnerAdapter(getStringArray(arrayResId))
fun <T> Context.createSimpleSpinnerAdapter(array: Array<T>): ArrayAdapter<T> = createSimpleSpinnerAdapter(array.toMutableList())
fun <T> Context.createSimpleSpinnerAdapter(elements: List<T>): ArrayAdapter<T> = ArrayAdapter(this, android.R.layout.simple_spinner_item, elements)
fun Context.getStringArray(@ArrayRes resId: Int): Array<String> = resources.getStringArray(resId)
fun Context.getDrawableCompat(@DrawableRes resId: Int): Drawable? = ContextCompat.getDrawable(this, resId)
fun Context.createSharedElement(view: View, @StringRes resId: Int): android.util.Pair<View, String> = createSharedElement(view, resources.getString(resId))