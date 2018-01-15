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

@SuppressLint("MissingPermission")
fun Context.isNetworkConnected(): Boolean = connectivityManager.activeNetworkInfo.isConnectedOrConnecting

fun Context.createSimpleSpinnerAdapter(vararg elements: String): ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_spinner_item, elements)
fun <T> Context.createSimpleSpinnerAdapter(elements: List<T>): ArrayAdapter<T> = ArrayAdapter(this, android.R.layout.simple_spinner_item, elements)
fun <T> Context.createSimpleSpinnerAdapter(elements: Array<T>): ArrayAdapter<T> = ArrayAdapter(this, android.R.layout.simple_spinner_item, elements)
fun Context.createSimpleSpinnerAdapter(@ArrayRes arrayResId: Int): ArrayAdapter<String> = createSimpleSpinnerAdapter(getStringArray(arrayResId))

fun Context.getStringArray(@ArrayRes resId: Int): Array<String> = resources.getStringArray(resId)
fun Context.getDrawableCompat(@DrawableRes resId: Int): Drawable? = ContextCompat.getDrawable(this, resId)
fun Context.createSharedElement(view: View, @StringRes resId: Int): android.util.Pair<View, String> = createSharedElement(view, resources.getString(resId))