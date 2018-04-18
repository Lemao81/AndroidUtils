package com.jueggs.resutils.accessory

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.support.annotation.DrawableRes
import android.support.v7.widget.RecyclerView
import android.view.View
import com.jueggs.andutils.extension.getDrawableCompat

class SimpleDivider(context: Context, @DrawableRes resId: Int) : RecyclerView.ItemDecoration() {
    private var drawable: Drawable? = context.getDrawableCompat(resId)

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        if (parent.getChildAdapterPosition(view) == 0 || drawable == null) return
        outRect.top += drawable!!.intrinsicHeight
    }

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val leftBound = parent.paddingLeft
        val rightBound = parent.width - parent.paddingRight

        (0 until parent.childCount).forEach {
            val child = parent.getChildAt(it)
            val layoutParams = child.layoutParams as RecyclerView.LayoutParams
            val topBound = child.bottom + layoutParams.bottomMargin
            if (drawable != null) {
                val bottomBound = topBound + drawable!!.intrinsicHeight
                drawable!!.setBounds(leftBound, topBound, rightBound, bottomBound)
                drawable!!.draw(canvas)
            }
        }
    }
}