package com.jueggs.andutils.callback

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View

class MoveListener(private val onMove: (event: MotionEvent) -> Unit) : View.OnTouchListener {
    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(view: View, event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_MOVE) {
            onMove(event)
            return true
        }
        return false
    }
}