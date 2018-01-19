package com.jueggs.utils.extension

import android.support.annotation.ArrayRes
import android.support.annotation.LayoutRes
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.jueggs.utils.R
import com.jueggs.utils.accessory.SimpleDivider

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

fun AdapterView<*>.asStringOrNull(): String? = if (selectedItem != null) selectedItem.toString() else null

fun AdapterView<*>.asString(): String = selectedItem.toString()

fun ViewGroup.layoutInflater(): LayoutInflater = LayoutInflater.from(context)

fun RecyclerView.setVerticalLinearLayoutManager(): RecyclerView {
    layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
    return this
}

fun RecyclerView.setHorizontalLinearLayoutManager(): RecyclerView {
    layoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
    return this
}

fun RecyclerView.setTheAdapter(adapter: RecyclerView.Adapter<*>): RecyclerView {
    this.adapter = adapter
    return this
}

fun RecyclerView.setSimpleDivider() = addItemDecoration(SimpleDivider(context, R.drawable.simple_divider))

fun Spinner.setSimpleAdapter(vararg elements: String): Spinner {
    adapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, elements)
    return this
}

fun <T> Spinner.setSimpleAdapter(elements: List<T>): Spinner {
    adapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, elements)
    return this
}

fun <T> Spinner.setSimpleAdapter(elements: Array<T>): Spinner {
    adapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, elements)
    return this
}

fun Spinner.setSimpleAdapter(@ArrayRes arrayResId: Int): Spinner = setSimpleAdapter(context.getStringArray(arrayResId))

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.visible() {
    visibility = View.VISIBLE
}