package com.jueggs.andutils.behavior

import android.content.Context
import android.support.design.widget.*
import android.util.AttributeSet
import android.view.Gravity.TOP
import android.view.View
import com.jueggs.andutils.isVerticalScroll

abstract class AbstractVerticalScrollBehavior<TView : View>(context: Context, attrs: AttributeSet) : CoordinatorLayout.Behavior<TView>(context, attrs) {
    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: TView, directTargetChild: View, target: View, axes: Int, type: Int) = isVerticalScroll(axes)
}