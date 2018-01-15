package com.jueggs.utils.extensions

import android.content.Context
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import com.jueggs.utils.R
import com.jueggs.utils.accessories.SimpleDivider

fun ViewGroup.inflate(@LayoutRes resId: Int): View = LayoutInflater.from(context).inflate(resId, this, false)

fun ViewGroup.show() {
    visibility = View.VISIBLE
}

fun ViewGroup.dismiss() {
    visibility = View.GONE
}

fun ViewGroup.collectCheckedCheckboxes(): List<CheckBox> {
    val result = arrayListOf<CheckBox>()
    (0..childCount).forEach {
        val child = getChildAt(it)
        if (child is CheckBox && child.isChecked) result.add(child)
    }
    return result
}

fun TextView.asString(): String = text.toString()
fun TextView.asInt(): Int = text.toString().toInt()

fun RecyclerView.setSimpleDivider() = addItemDecoration(SimpleDivider(context, R.drawable.simple_divider))

fun AdapterView<*>.asStringOrNull(): String? = if (selectedItem != null) selectedItem.toString() else null

fun AdapterView<*>.asString(): String = selectedItem.toString()

fun ViewGroup.layoutInflater(): LayoutInflater = LayoutInflater.from(context)

fun RecyclerView.setVerticalLinearLayoutManager(context: Context): RecyclerView {
    layoutManager = context.verticalLinearLayoutManager()
    return this
}

fun RecyclerView.setTheAdapter(adapter: RecyclerView.Adapter<*>): RecyclerView {
    this.adapter = adapter
    return this
}