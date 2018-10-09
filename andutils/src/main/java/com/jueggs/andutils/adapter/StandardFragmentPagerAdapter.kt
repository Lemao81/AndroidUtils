package com.jueggs.andutils.adapter

import android.content.Context
import android.support.v4.app.*

class StandardFragmentPagerAdapter(private val factoryList: List<() -> Fragment>, private val pageTitleArrayResId: Int, context: Context?, fragmentManager: FragmentManager)
    : AbstractFragmentPagerAdapter(context, fragmentManager) {
    override fun getTitles(): Int = pageTitleArrayResId

    override fun getFragmentFactoryList(): List<() -> Fragment> = factoryList
}