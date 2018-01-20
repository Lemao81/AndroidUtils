package com.jueggs.utils.extension

import android.content.Context
import android.content.SharedPreferences
import android.support.annotation.ArrayRes
import android.support.annotation.StringRes

fun SharedPreferences.getBoolean(context: Context, @StringRes resId: Int): Boolean = getBoolean(context.getString(resId), false)

fun SharedPreferences.getString(context: Context, @StringRes resId: Int): String = getString(context.getString(resId), "")

fun SharedPreferences.getListPreferenceEntry(context: Context, @StringRes resIdKey: Int, @ArrayRes resIdEntryArray: Int): String {
    val index = getString(context, resIdKey).toInt()
    return context.getStringArray(resIdEntryArray)[index]
}