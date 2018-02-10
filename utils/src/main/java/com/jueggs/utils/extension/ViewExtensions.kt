package com.jueggs.utils.extension

import android.support.annotation.*
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.jueggs.utils.*
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

fun View.addEnableTextWatcher(vararg inputFields: EditText) {
    isEnabled = hasText(*inputFields)
    val watcher = object : TextWatcherAdapter() {
        override fun afterTextChanged(text: Editable?) {
            isEnabled = hasText(*inputFields)
        }
    }
    inputFields.forEach { it.addTextChangedListener(watcher) }
}

fun View.indefiniteSnackbar(message: CharSequence, actionText: CharSequence? = null, action: ((View) -> Unit)? = null) = createSnackbar(this, message, Snackbar.LENGTH_INDEFINITE, actionText, action)
fun View.indefiniteSnackbar(@StringRes messageId: Int, @StringRes actionTextId: Int? = null, action: ((View) -> Unit)? = null) =
        indefiniteSnackbar(resources.getString(messageId), if (actionTextId != null) resources.getString(actionTextId) else null, action)

fun View.longSnackbar(message: CharSequence, actionText: CharSequence? = null, action: ((View) -> Unit)? = null) = createSnackbar(this, message, Snackbar.LENGTH_LONG, actionText, action)
fun View.longSnackbar(@StringRes messageId: Int, @StringRes actionTextId: Int? = null, action: ((View) -> Unit)? = null) =
        longSnackbar(resources.getString(messageId), if (actionTextId != null) resources.getString(actionTextId) else null, action)

fun View.shortSnackbar(message: CharSequence, actionText: CharSequence? = null, action: ((View) -> Unit)? = null) = createSnackbar(this, message, Snackbar.LENGTH_SHORT, actionText, action)
fun View.shortSnackbar(@StringRes messageId: Int, @StringRes actionTextId: Int? = null, action: ((View) -> Unit)? = null) =
        shortSnackbar(resources.getString(messageId), if (actionTextId != null) resources.getString(actionTextId) else null, action)

private fun createSnackbar(view: View, message: CharSequence, period: Int, actionText: CharSequence? = null, action: ((View) -> Unit)? = null) {
    val snackbar = Snackbar.make(view, message, period)
    if (actionText != null && action != null)
        snackbar.setAction(actionText, action)
    snackbar.show()
}