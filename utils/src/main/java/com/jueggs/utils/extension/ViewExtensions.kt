package com.jueggs.utils.extension

import android.support.annotation.*
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.jueggs.utils.R
import com.jueggs.utils.accessory.SimpleDivider
import com.jueggs.utils.isLollipopOrAboveUtil

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

inline fun <reified T : View> ViewGroup.findViews(): List<T> = (0..childCount).filter { getChildAt(it) is T }.map { it as T }

fun TextView.asString(): String = text.toString()

fun TextView.asInt(): Int = text.toString().toInt()

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

fun RecyclerView.withSimpleDivider() = addItemDecoration(SimpleDivider(context, R.drawable.simple_divider))

fun <T> RecyclerView.Adapter<*>.setAdapterListItems(newItems: List<T>, currentItems: MutableList<T>, notifyChange: Boolean = true) {
    currentItems.clear()
    currentItems.addAll(newItems)
    if (notifyChange) notifyDataSetChanged()
}

fun Spinner.withSimpleAdapter(vararg elements: String): Spinner {
    adapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, elements)
    return this
}

fun <T> Spinner.withSimpleAdapter(elements: List<T>): Spinner {
    adapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, elements)
    return this
}

fun <T> Spinner.withSimpleAdapter(elements: Array<T>): Spinner {
    adapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, elements)
    return this
}

fun Spinner.withSimpleAdapter(@ArrayRes arrayResId: Int): Spinner = withSimpleAdapter(context.getStringArray(arrayResId))

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.withTransitionName(name: String): View {
    if (isLollipopOrAboveUtil())
        transitionName = name
    return this
}

fun View.withTransitionName(@StringRes resId: Int): View = withTransitionName(context.getString(resId))