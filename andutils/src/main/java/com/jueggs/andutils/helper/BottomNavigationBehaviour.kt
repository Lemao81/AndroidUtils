package com.jueggs.andutils.helper

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet
import android.view.View
import com.jueggs.jutils.cropToRange

class BottomNavigationBehaviour<TView : View>(context: Context, attrs: AttributeSet) : CoordinatorLayoutVerticalScrollBehaviour<TView>(context, attrs) {
    override fun onNestedPreScroll(coordinatorLayout: CoordinatorLayout, child: TView, target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
        child.translationY = cropToRange(0f, child.height.toFloat(), child.translationY + dy)
    }
}