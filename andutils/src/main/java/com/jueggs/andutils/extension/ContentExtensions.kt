package com.jueggs.andutils.extension

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Parcelable
import android.support.annotation.*
import java.security.InvalidParameterException

fun SharedPreferences.getBoolean(context: Context, @StringRes resId: Int): Boolean = getBoolean(context.getString(resId), false)

fun SharedPreferences.getString(context: Context, @StringRes resId: Int): String = getString(context.getString(resId), "") ?: ""

fun SharedPreferences.getListPreferenceEntry(context: Context, @StringRes resIdKey: Int, @ArrayRes resIdEntryArray: Int): String {
    val index = getString(context, resIdKey).toInt()
    return context.getStringArray(resIdEntryArray)[index]
}

fun Intent.withExtra(vararg extras: Pair<String, Any>): Intent {
    extras.forEach { (key, value) ->
        when (value) {
            is Parcelable -> putExtra(key, value)
            is Int -> putExtra(key, value)
            is Double -> putExtra(key, value)
            is Boolean -> putExtra(key, value)
            is String -> putExtra(key, value)
            is ArrayList<*> -> {
                if (value.any() && value[0] is String) {
                    val list = value as? ArrayList<String>
                    list?.let { putStringArrayListExtra(key, it) }
                }
            }
            else -> throw InvalidParameterException("Unknown value type, extend extension method")
        }
    }
    return this
}