package com.jueggs.andutils.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.jueggs.andutils.Util.isHorizontalScroll

abstract class AbstractHorizontalScrollBehavior<TView : View>(context: Context, attrs: AttributeSet) : CoordinatorLayout.Behavior<TView>(context, attrs) {
    protected var acceleration: Float = 1f

    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: TView, directTargetChild: View, target: View, axes: Int, type: Int) = isHorizontalScroll(axes)
}