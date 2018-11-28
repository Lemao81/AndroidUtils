package com.jueggs.andutils

import android.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.text.Html
import android.view.View
import android.view.View.*
import android.widget.*
import com.jueggs.andutils.extension.loadOrDefault
import com.jueggs.andutils.helper.*
import com.jueggs.jutils.extension.join
import com.jueggs.jutils.extension.toAge
import org.joda.time.DateTime
import org.joda.time.LocalDate

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

@BindingAdapter(value = ["imageUrl", "defaultImage", "placeholder", "circleCrop"], requireAll = false)
fun ImageView.setImageUrl(url: String, default: Drawable?, placeholder: Drawable?, circleCrop: Boolean?) {
    val glideRequest = if (default != null) GlideApp.with(context).loadOrDefault(url, default)
    else GlideApp.with(context).load(url)
    if (placeholder != null) glideRequest.placeholder(placeholder)
    if (circleCrop != null && circleCrop) glideRequest.circleCrop()
    glideRequest.into(this)
}

/**
 * Attribute to set the font typeface by data binding. Usage: app:typeface="@{`<fontnamewithoutfileextension>`}"
 */
@BindingAdapter("typeface")
fun TextView.setTypefaceBinded(fontName: String) {
    typeface = FontCache.getInstance(context)[fontName]
}

@BindingAdapter(value = ["date", "dateFormat"], requireAll = false)
fun TextView.setDate(date: LocalDate?, format: String?) {
    text = date?.toString(format ?: DEFAULT_DATE_FORMAT)
}

@BindingAdapter(value = ["renderedDate", "renderedDateFormat"], requireAll = false)
fun TextView.setRenderedDate(dateTime: DateTime?, format: String?) {
    text = dateTime?.let { DateTimeRenderer(format).render(dateTime) }
}

@BindingAdapter(value = ["join", "separator"], requireAll = false)
fun TextView.setJoin(list: List<Any>, separator: String?) {
    text = list.join(separator ?: ", ")
}

@BindingAdapter("html")
fun TextView.setHtml(html: String) {
    text = if (isNougatOrAboveUtil()) Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT).toString() else Html.fromHtml(html).toString()
}

@BindingAdapter("number")
fun TextView.setNumber(number: Number) {
    text = number.toString()
}

@BindingAdapter("age")
fun TextView.setAge(dateOfBirth: LocalDate?) {
    text = dateOfBirth?.toAge()?.toString()
}