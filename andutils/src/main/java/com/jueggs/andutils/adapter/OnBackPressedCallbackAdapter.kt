package com.jueggs.andutils.adapter

import androidx.activity.OnBackPressedCallback

class OnBackPressedCallbackAdapter(private val onBackPressed: () -> Unit) : OnBackPressedCallback(true) {
    override fun handleOnBackPressed() = onBackPressed()
}