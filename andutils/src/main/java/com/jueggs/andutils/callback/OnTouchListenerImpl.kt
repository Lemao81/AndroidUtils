package com.jueggs.andutils.callback

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_DOWN
import android.view.MotionEvent.ACTION_MOVE
import android.view.MotionEvent.ACTION_UP
import android.view.View

class OnTouchListenerImpl : View.OnTouchListener {
    private var onTapUpFunc: ((View, MotionEvent) -> Unit)? = null
    private var onTapDownFunc: ((View, MotionEvent) -> Unit)? = null
    private var onMoveFunc: ((View, MotionEvent) -> Unit)? = null

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(view: View, event: MotionEvent): Boolean {
        when (event.action) {
            ACTION_UP -> {
                if (onTapUpFunc != null) {
                    onTapUpFunc?.invoke(view, event)
                    return true
                }
            }
            ACTION_DOWN -> {
                if (onTapDownFunc != null) {
                    onTapDownFunc?.invoke(view, event)
                    return true
                }
            }
            ACTION_MOVE -> {
                if (onMoveFunc != null) {
                    onMoveFunc?.invoke(view, event)
                    return true
                }
            }
        }
        return false
    }

    fun onTapUp(func: (View, MotionEvent) -> Unit) {
        onTapUpFunc = func
    }

    fun onTapDown(func: (View, MotionEvent) -> Unit) {
        onTapDownFunc = func
    }

    fun onMove(func: (View, MotionEvent) -> Unit) {
        onMoveFunc = func
    }
}

fun View._setOnTouchListener(block: OnTouchListenerImpl.() -> Unit) = setOnTouchListener(OnTouchListenerImpl().apply(block))