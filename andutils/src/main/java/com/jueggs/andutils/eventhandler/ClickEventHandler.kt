package com.jueggs.andutils.eventhandler

import android.view.View

class ClickEventHandler(private val clickHandler: (View, String) -> Unit = { _, _ -> }, private val longClickHandler: (View, String) -> Boolean = { _, _ -> false }) {
    fun onClick(view: View, uid: String) = clickHandler(view, uid)
    fun onLongClick(view: View, uid: String): Boolean = longClickHandler(view, uid)
}