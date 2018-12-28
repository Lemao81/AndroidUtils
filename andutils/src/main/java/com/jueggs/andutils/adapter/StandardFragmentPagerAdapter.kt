package com.jueggs.andutils.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class StandardFragmentPagerAdapter(
    fragmentManager: FragmentManager,
    private val fragments: List<Fragment>,
    private val titleArray: Array<String>
) : FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int) = fragments[position]

    override fun getCount() = fragments.size

    override fun getPageTitle(position: Int): CharSequence? = if (titleArray.size != count) throw IllegalStateException("Page title count does not equal fragment count") else titleArray[position]
}