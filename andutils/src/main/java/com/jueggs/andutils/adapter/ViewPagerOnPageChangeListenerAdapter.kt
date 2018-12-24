package com.jueggs.andutils.adapter

import androidx.viewpager.widget.ViewPager

class ViewPagerOnPageChangeListenerImpl : ViewPager.OnPageChangeListener {
    private var onPageScrollStateChangedFunc: ((Int) -> Unit)? = null
    private var onPageScrolledFunc: ((Int, Float, Int) -> Unit)? = null
    private var onPageSelectedFunc: ((Int) -> Unit)? = null

    fun _onPageScrollStateChanged(func: (Int) -> Unit) {
        onPageScrollStateChangedFunc = func
    }

    fun _onPageScrolled(func: (Int, Float, Int) -> Unit) {
        onPageScrolledFunc = func
    }

    fun _onPageSelected(func: (Int) -> Unit) {
        onPageSelectedFunc = func
    }

    override fun onPageScrollStateChanged(state: Int) = onPageScrollStateChangedFunc?.invoke(state) ?: Unit

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) = onPageScrolledFunc?.invoke(position, positionOffset, positionOffsetPixels) ?: Unit

    override fun onPageSelected(position: Int) = onPageSelectedFunc?.invoke(position) ?: Unit
}

fun ViewPager._addOnPageChangeListener(block: ViewPagerOnPageChangeListenerImpl.() -> Unit) = addOnPageChangeListener(ViewPagerOnPageChangeListenerImpl().apply(block))