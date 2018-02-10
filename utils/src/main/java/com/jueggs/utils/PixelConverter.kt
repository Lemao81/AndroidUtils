package com.jueggs.utils

import android.content.Context
import android.util.TypedValue

class PixelConverter(var context: Context) {
    fun dpToPixel(dips: Int) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dips.toFloat(), context.resources.displayMetrics)
}