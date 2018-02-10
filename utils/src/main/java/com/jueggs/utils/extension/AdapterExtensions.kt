package com.jueggs.utils.extension

import android.support.annotation.LayoutRes
import android.widget.ArrayAdapter

fun <T> ArrayAdapter<T>.withSimpleSpinnerDropdown(): ArrayAdapter<T> {
    setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    return this
}

fun <T> ArrayAdapter<T>.withDropDownViewResource(@LayoutRes resId: Int): ArrayAdapter<T> {
    setDropDownViewResource(resId)
    return this
}