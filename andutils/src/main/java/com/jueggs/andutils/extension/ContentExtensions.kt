package com.jueggs.andutils.extension

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Parcelable
import androidx.annotation.ArrayRes
import androidx.annotation.StringRes
import java.security.InvalidParameterException

fun SharedPreferences.getBoolean(context: Context, @StringRes resId: Int): Boolean = getBoolean(context.getString(resId), false)

fun SharedPreferences.getString(context: Context, @StringRes resId: Int): String = getString(context.getString(resId), "") ?: ""

fun SharedPreferences.getListPreferenceEntry(context: Context, @StringRes resIdKey: Int, @ArrayRes resIdEntryArray: Int): String {
    val index = getString(context, resIdKey).toInt()
    return context.getStringArray(resIdEntryArray)[index]
}