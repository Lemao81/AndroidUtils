package com.jueggs.andutils.extension

import android.support.annotation.LayoutRes
import android.support.v7.widget.*
import android.view.*
import android.widget.*

inline fun <reified T : View> ViewGroup.findViews(): List<T> = (0..childCount).filter { getChildAt(it) is T }.map { it as T }

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

fun ViewGroup.layoutInflater(): LayoutInflater = LayoutInflater.from(context)

fun AdapterView<*>.asStringOrNull(): String? = if (selectedItem != null) selectedItem.toString() else null

fun AdapterView<*>.asString(): String = selectedItem.toString()

fun RecyclerView.withVerticalLinearLayoutManager(): RecyclerView {
    layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
    return this
}

fun RecyclerView.withHorizontalLinearLayoutManager(): RecyclerView {
    layoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
    return this
}

fun RecyclerView.withAdapter(adapter: RecyclerView.Adapter<*>): RecyclerView {
    this.adapter = adapter
    return this
}

fun <T> RecyclerView.Adapter<*>.setAdapterListItems(newItems: List<T>, currentItems: MutableList<T>, notifyChange: Boolean = true) {
    currentItems.clear()
    currentItems.addAll(newItems)
    if (notifyChange) notifyDataSetChanged()
}