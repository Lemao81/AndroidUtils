package com.jueggs.andutils.adapter

import android.content.Context
import android.support.v4.app.*
import com.jueggs.andutils.extension.getStringArray
import com.jueggs.jutils.exception.InconsistentStateException

abstract class AbstractFragmentPagerAdapter(val context: Context?, fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    private var fragmentFactoryList: List<() -> Fragment>? = null
    private var pageTitles: Array<String>? = null

    override fun getItem(position: Int): Fragment {
        if (fragmentFactoryList == null)
            fragmentFactoryList = getFragmentFactoryList()

        return (fragmentFactoryList as List<() -> Fragment>)[position]()
    }

    override fun getCount(): Int {
        if (fragmentFactoryList == null)
            fragmentFactoryList = getFragmentFactoryList()

        return fragmentFactoryList?.size ?: 0
    }

    override fun getPageTitle(position: Int): CharSequence? {
        if (pageTitles == null) {
            pageTitles = context?.getStringArray(getTitles())

            if (pageTitles?.size != count)
                throw InconsistentStateException("Page title count does not equal fragment count")
        }

        return (pageTitles as Array<String>)[position]
    }

    abstract fun getTitles(): Int
    abstract fun getFragmentFactoryList(): List<() -> Fragment>
}