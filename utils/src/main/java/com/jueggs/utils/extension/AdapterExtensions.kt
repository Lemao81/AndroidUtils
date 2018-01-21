package com.jueggs.utils.extension

import android.widget.ArrayAdapter

fun <T> ArrayAdapter<T>.withSimpleSpinnerDropdown(): ArrayAdapter<T> {
    setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    return this
}