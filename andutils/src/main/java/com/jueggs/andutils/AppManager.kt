package com.jueggs.andutils

import android.content.Context
import android.util.DisplayMetrics
import com.jueggs.jutils.pairOf
import org.jetbrains.anko.windowManager
import org.koin.core.KoinComponent
import org.koin.core.inject

object AppManager : KoinComponent {
    private val context by inject<Context>()

    val screenWidth = DisplayMetrics().apply { context.windowManager.defaultDisplay.getMetrics(this) }.widthPixels
    val screenHeight = DisplayMetrics().apply { context.windowManager.defaultDisplay.getMetrics(this) }.heightPixels
    val screenMetrics = DisplayMetrics().apply { context.windowManager.defaultDisplay.getMetrics(this) }.let { pairOf(it.widthPixels, it.heightPixels) }
    var isSinglePane = true
    val isTwoPane = !isSinglePane
}