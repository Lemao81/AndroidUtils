package com.jueggs.andutils.helper

import android.annotation.SuppressLint
import android.view.*

class TapUpListener(private val onTabUp: () -> Unit) : View.OnTouchListener {
    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View, event: MotionEvent) =
            when (event.action) {
                MotionEvent.ACTION_DOWN -> true
                MotionEvent.ACTION_UP -> {
                    onTabUp()
                    true
                }
                else -> false
            }
}