package com.jueggs.andutils.helper

import android.content.Context
import android.support.design.widget.*
import android.util.AttributeSet
import android.view.Gravity.TOP
import android.view.View
import com.jueggs.andutils.isVerticalScroll

abstract class CoordinatorLayoutVerticalScrollBehaviour<TView : View>(context: Context, attrs: AttributeSet) : CoordinatorLayout.Behavior<TView>(context, attrs) {
    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: TView, directTargetChild: View, target: View, axes: Int, type: Int) = isVerticalScroll(axes)

    override fun layoutDependsOn(parent: CoordinatorLayout?, child: TView, dependency: View?): Boolean {
        if (dependency is Snackbar.SnackbarLayout)
            updateSnackbar(child, dependency)
        return super.layoutDependsOn(parent, child, dependency)
    }

    private fun updateSnackbar(child: TView, snackbarLayout: Snackbar.SnackbarLayout) {
        if (snackbarLayout.layoutParams is CoordinatorLayout.LayoutParams) {
            val params = snackbarLayout.layoutParams as CoordinatorLayout.LayoutParams
            params.anchorId = child.id
            params.anchorGravity = TOP
            params.gravity = TOP
            snackbarLayout.layoutParams = params
        }
    }
}