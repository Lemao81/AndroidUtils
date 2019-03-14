package com.jueggs.andutils.callback

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View

class TapDownListener(private val onTapDown: (event: MotionEvent) -> Unit) : View.OnTouchListener {
    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View, event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            onTapDown(event)
            return true
        }
        return false
    }
}