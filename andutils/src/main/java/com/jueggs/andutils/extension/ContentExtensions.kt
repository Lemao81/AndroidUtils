package com.jueggs.andutils.extension

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Parcelable
import android.support.annotation.*
import android.support.v4.content.ContextCompat
import java.security.InvalidParameterException

fun SharedPreferences.getBoolean(context: Context, @StringRes resId: Int): Boolean = getBoolean(context.getString(resId), false)

fun SharedPreferences.getString(context: Context, @StringRes resId: Int): String = getString(context.getString(resId), "")

fun SharedPreferences.getListPreferenceEntry(context: Context, @StringRes resIdKey: Int, @ArrayRes resIdEntryArray: Int): String {
    val index = getString(context, resIdKey).toInt()
    return context.getStringArray(resIdEntryArray)[index]
}

fun Context.getColorCompat(@ColorRes resId: Int) = ContextCompat.getColor(this, resId)

fun Intent.withExtra(vararg extras: Pair<String, Any>): Intent {
    extras.forEach { extra ->
        when {
            extra.second is Parcelable -> putExtra(extra.first, extra.second as Parcelable)
            extra.second is Int -> putExtra(extra.first, extra.second as Int)
            extra.second is Double -> putExtra(extra.first, extra.second as Double)
            extra.second is Boolean -> putExtra(extra.first, extra.second as Boolean)
            extra.second is String -> putExtra(extra.first, extra.second as String)
            extra.second is ArrayList<*> -> {
                if ((extra.second as ArrayList<*>).any() && (extra.second as ArrayList<*>)[0] is String)
                    putStringArrayListExtra(extra.first, extra.second as ArrayList<String>)
            }
            else -> throw InvalidParameterException("Unknown value type, extend extension method")
        }
    }
    return this
}

fun Bundle.withData(vararg data: Pair<String, Any>): Bundle {
    data.forEach { datum ->
        when {
            datum.second is Parcelable -> putParcelable(datum.first, datum.second as Parcelable)
            datum.second is Int -> putInt(datum.first, datum.second as Int)
            datum.second is Double -> putDouble(datum.first, datum.second as Double)
            datum.second is Boolean -> putBoolean(datum.first, datum.second as Boolean)
            datum.second is String -> putString(datum.first, datum.second as String)
            datum.second is ArrayList<*> -> {
                if ((datum.second as ArrayList<*>).any() && (datum.second as ArrayList<*>)[0] is String)
                    putStringArrayList(datum.first, datum.second as ArrayList<String>)
            }
            else -> throw InvalidParameterException("Unknown value type, extend extension method")
        }
    }
    return this
}