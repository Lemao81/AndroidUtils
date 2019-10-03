package com.jueggs.andutils.extension

import android.content.Intent
import android.net.Uri
import android.os.Parcelable
import java.security.InvalidParameterException

fun Intent.withAction(action: String): Intent {
    this.action = action

    return this
}

fun Intent.withData(data: Uri): Intent {
    this.data = data

    return this
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