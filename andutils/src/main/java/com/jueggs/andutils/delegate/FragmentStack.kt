package com.jueggs.andutils.delegate

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

interface FragmentStack {
    fun addFragment(fragmentManager: FragmentManager?, @IdRes containerViewId: Int, fragment: Fragment)
    fun replaceFragment(fragmentManager: FragmentManager?, @IdRes containerViewId: Int, fragment: Fragment)
    fun popFragment(fragmentManager: FragmentManager?)
}