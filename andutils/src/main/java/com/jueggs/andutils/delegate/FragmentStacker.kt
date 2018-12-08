package com.jueggs.andutils.delegate

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

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