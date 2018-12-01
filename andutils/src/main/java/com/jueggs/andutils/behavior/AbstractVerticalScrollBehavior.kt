package com.jueggs.andutils.behavior

import android.content.Context
import android.support.design.widget.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes
import com.jueggs.andutils.*
import com.jueggs.andutils.Util.isVerticalScroll

abstract class AbstractVerticalScrollBehavior<TView : View>(context: Context, attrs: AttributeSet) : CoordinatorLayout.Behavior<TView>(context, attrs) {
    protected var acceleration: Float = 1f
    protected var gravity: Int = TOP

    init {
        context.withStyledAttributes(attrs, R.styleable.VerticalScrollBehavior) {
            gravity = getInteger(R.styleable.VerticalScrollBehavior_behavior_gravity, TOP)
            acceleration = getFloat(R.styleable.VerticalScrollBehavior_behavior_acceleration, 1f)
        }
    }

    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: TView, directTargetChild: View, target: View, axes: Int, type: Int) = isVerticalScroll(axes)

    companion object {
        const val TOP: Int = 0
        const val BOTTOM: Int = 1
    }
}