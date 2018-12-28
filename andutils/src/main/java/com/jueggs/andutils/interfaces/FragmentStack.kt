package com.jueggs.andutils.interfaces

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

interface FragmentStack {
    fun addFragment(fragmentManager: FragmentManager?, @IdRes containerViewId: Int, fragment: Fragment)
    fun replaceFragment(fragmentManager: FragmentManager?, @IdRes containerViewId: Int, fragment: Fragment)
    fun popFragment(fragmentManager: FragmentManager?)
}