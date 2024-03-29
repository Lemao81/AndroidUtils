package com.jueggs.andutils.callback

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View

class CompoundTouchListener : View.OnTouchListener {
    private val listeners: MutableList<View.OnTouchListener> = mutableListOf()

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(view: View, event: MotionEvent): Boolean {
        var consumed = false

        listeners.forEach {
            if (it.onTouch(view, event)) {
                consumed = true
            }
        }
        return consumed
    }

    fun add(listener: View.OnTouchListener) = listeners.add(listener)

    fun addAll(listeners: List<View.OnTouchListener>) = this.listeners.addAll(listeners)

    operator fun plusAssign(listener: View.OnTouchListener) {
        listeners += listener
    }
}