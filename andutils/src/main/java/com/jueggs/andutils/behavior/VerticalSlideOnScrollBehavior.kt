package com.jueggs.andutils.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.snackbar.Snackbar
import com.jueggs.jutils.extension.cropTo

class VerticalSlideOnScrollBehavior<TView : View>(context: Context, attrs: AttributeSet) : AbstractVerticalScrollBehavior<TView>(context, attrs) {

    override fun onNestedPreScroll(coordinatorLayout: CoordinatorLayout, child: TView, target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)

        val childHeight = child.height.toFloat()
        val newTranslationY = when (gravity) {
            TOP -> child.translationY - dy * acceleration
            BOTTOM -> child.translationY + dy * acceleration
            else -> throw IllegalStateException("Gravity must be 0 or 1")
        }

        child.translationY = when (gravity) {
            TOP -> newTranslationY.cropTo(-childHeight, 0f)
            BOTTOM -> newTranslationY.cropTo(0f, childHeight)
            else -> throw IllegalStateException("Gravity must be 0 or 1")
        }
    }

    override fun layoutDependsOn(parent: CoordinatorLayout, child: TView, dependency: View): Boolean {
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
}