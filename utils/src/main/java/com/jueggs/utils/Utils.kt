package com.jueggs.utils

import android.util.Log
import android.view.View

fun createSharedElement(view: View, transitionName: String): android.util.Pair<View, String> = android.util.Pair(view, transitionName)

fun logTagged(tag: String, text: Any, prefix: String) = Log.d(tag, if (!prefix.isEmpty()) "$prefix:    $text" else text.toString())

fun logDebug(text: Any, prefix: String = EMPTY_STRING) = logTagged(TAG_DEBUG, text, prefix)

fun logNetwork(text: Any, prefix: String = EMPTY_STRING) = logTagged(TAG_NETWORK, text, prefix)