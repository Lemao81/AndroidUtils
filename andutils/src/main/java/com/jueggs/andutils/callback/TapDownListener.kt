package com.jueggs.andutils.callback

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View

class TapDownListener(private val onTabDown: () -> Unit) : View.OnTouchListener {
    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View, event: MotionEvent) =
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                onTabDown()
                true
            }
            MotionEvent.ACTION_UP -> true
            else -> false
        }
}