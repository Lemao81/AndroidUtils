package com.jueggs.utils

import android.view.View

fun createSharedElement(view: View, transitionName: String): android.util.Pair<View, String> = android.util.Pair(view, transitionName)