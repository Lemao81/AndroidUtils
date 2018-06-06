package com.jueggs.andutils.behavior

import android.content.Context
import android.support.design.widget.*
import android.util.AttributeSet
import android.view.*
import androidx.core.content.withStyledAttributes
import com.jueggs.andutils.R
import com.jueggs.jutils.cropToRange

class VerticalSlideOnScrollBehavior<TView : View>(context: Context, attrs: AttributeSet) : AbstractVerticalScrollBehavior<TView>(context, attrs) {
    private var gravity: Int = TOP
    private var acceleration: Float = 1f

    init {
        context.withStyledAttributes(attrs, R.styleable.VerticalScrollBehavior) {
            gravity = getInteger(R.styleable.VerticalScrollBehavior_behavior_gravity, TOP)
            acceleration = getFloat(R.styleable.VerticalScrollBehavior_behavior_acceleration, 1f)
        }
    }

    override fun onNestedPreScroll(coordinatorLayout: CoordinatorLayout, child: TView, target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)

        val childHeight = child.height.toFloat()
        val newTranslationY = when (gravity) {
            TOP -> child.translationY - dy * acceleration
            BOTTOM -> child.translationY + dy * acceleration
            else -> throw IllegalStateException("Gravity must be 0 or 1")
        }

        child.translationY = when (gravity) {
            TOP -> cropToRange(-childHeight, 0f, newTranslationY)
            BOTTOM -> cropToRange(0f, childHeight, newTranslationY)
            else -> throw IllegalStateException("Gravity must be 0 or 1")
        }
    }

    override fun layoutDependsOn(parent: CoordinatorLayout?, child: TView, dependency: View?): Boolean {
        if (dependency is Snackbar.SnackbarLayout && gravity == BOTTOM)
            updateSnackbar(child, dependency)
        return super.layoutDependsOn(parent, child, dependency)
    }

    private fun updateSnackbar(child: TView, snackbarLayout: Snackbar.SnackbarLayout) {
        if (snackbarLayout.layoutParams is CoordinatorLayout.LayoutParams) {
            val params = snackbarLayout.layoutParams as CoordinatorLayout.LayoutParams
            params.anchorId = child.id
            params.anchorGravity = Gravity.TOP
            params.gravity = Gravity.TOP
            snackbarLayout.layoutParams = params
        }
    }

    companion object {
        const val TOP: Int = 0
        const val BOTTOM: Int = 1
    }
}