package com.jueggs.andutils.delegate

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

object FragmentStacker : FragmentStack {
    override fun addFragment(fragmentManager: FragmentManager?, @IdRes containerViewId: Int, fragment: Fragment) {
        fragmentManager?.beginTransaction()?.add(containerViewId, fragment)?.addToBackStack(fragment::class.simpleName)?.commit()
    }

    override fun replaceFragment(fragmentManager: FragmentManager?, @IdRes containerViewId: Int, fragment: Fragment) {
        fragmentManager?.beginTransaction()?.replace(containerViewId, fragment)?.commit()
    }

    override fun popFragment(fragmentManager: FragmentManager?) {
        fragmentManager?.popBackStack()
    }
}