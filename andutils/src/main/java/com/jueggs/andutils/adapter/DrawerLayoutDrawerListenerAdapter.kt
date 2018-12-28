package com.jueggs.andutils.adapter

import android.view.View
import androidx.drawerlayout.widget.DrawerLayout

class DrawerLayoutDrawerListenerImpl : DrawerLayout.DrawerListener {
    private var onDrawerStateChangedFunc: ((Int) -> Unit)? = null
    private var onDrawerSlideFunc: ((View, Float) -> Unit)? = null
    private var onDrawerClosedFunc: ((View) -> Unit)? = null
    private var onDrawerOpenedFunc: ((View) -> Unit)? = null

    fun _onDrawerStateChanged(func: (Int) -> Unit) {
        onDrawerStateChangedFunc = func
    }

    fun _onDrawerSlide(func: (View, Float) -> Unit) {
        onDrawerSlideFunc = func
    }

    fun _onDrawerClosed(func: (View) -> Unit) {
        onDrawerClosedFunc = func
    }

    fun _onDrawerOpened(func: (View) -> Unit) {
        onDrawerOpenedFunc = func
    }

    override fun onDrawerStateChanged(newState: Int) = onDrawerStateChangedFunc?.invoke(newState) ?: Unit

    override fun onDrawerSlide(drawerView: View, slideOffset: Float) = onDrawerSlideFunc?.invoke(drawerView, slideOffset) ?: Unit

    override fun onDrawerClosed(drawerView: View) = onDrawerClosedFunc?.invoke(drawerView) ?: Unit

    override fun onDrawerOpened(drawerView: View) = onDrawerOpenedFunc?.invoke(drawerView) ?: Unit
}