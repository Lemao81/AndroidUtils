package com.jueggs.andutils.extension

import android.widget.ArrayAdapter
import androidx.annotation.LayoutRes

fun <T> ArrayAdapter<T>.withSimpleSpinnerDropdown(): ArrayAdapter<T> {
    setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    return this
}

fun <T> ArrayAdapter<T>.withDropDownViewResource(@LayoutRes resId: Int): ArrayAdapter<T> {
    setDropDownViewResource(resId)
    return this
}