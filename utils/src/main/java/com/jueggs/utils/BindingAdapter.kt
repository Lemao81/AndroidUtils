package com.jueggs.utils

import android.databinding.BindingAdapter
import android.view.View
import android.view.View.*
import android.widget.TextView
import com.jueggs.utils.helper.FontCache

@set:BindingAdapter("visibleOrGone")
var View.visibleOrGone
    get() = visibility == VISIBLE
    set(value) {
        visibility = if (value) VISIBLE else GONE
    }

@set:BindingAdapter("visible")
var View.visible
    get() = visibility == VISIBLE
    set(value) {
        visibility = if (value) VISIBLE else INVISIBLE
    }

@set:BindingAdapter("invisible")
var View.invisible
    get() = visibility == INVISIBLE
    set(value) {
        visibility = if (value) INVISIBLE else VISIBLE
    }

@set:BindingAdapter("gone")
var View.gone
    get() = visibility == GONE
    set(value) {
        visibility = if (value) GONE else VISIBLE
    }

/**
 * Attribute to set the font typeface by data binding. Usage: app:typeface="@{`<fontnamewithoutfileextension>`}"
 */
@BindingAdapter("bind:typeface")
fun TextView.setTypefaceBinded(textView: TextView, fontName: String) {
    typeface = FontCache.getInstance(textView.context)[fontName]
}