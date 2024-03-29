package com.jueggs.andutils.extension

import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.AccelerateInterpolator
import android.view.animation.AnticipateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.Spinner
import androidx.annotation.ArrayRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.jueggs.andutils.Util.haveAllText
import com.jueggs.andutils.adapter._addTextChangedListener
import com.jueggs.andutils.isLollipopOrAboveUtil

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

fun <T> AutoCompleteTextView.withSimpleAdapter(elements: List<T>): AutoCompleteTextView {
    setAdapter(ArrayAdapter(context, android.R.layout.simple_dropdown_item_1line, elements))

    return this
}

fun View.makeVisible() = let { visibility = View.VISIBLE }
fun View.makeInvisible() = let { visibility = View.INVISIBLE }
fun View.makeGone() = let { visibility = View.GONE }

fun View.withTransitionName(name: String): View {
    if (isLollipopOrAboveUtil()) {
        transitionName = name
    }

    return this
}

fun View.withTransitionName(@StringRes resId: Int): View = withTransitionName(context.getString(resId))

fun View.addEnableTextWatcher(vararg inputFields: EditText) {
    isEnabled = haveAllText(*inputFields)

    inputFields.forEach {
        it._addTextChangedListener {
            _afterTextChanged { isEnabled = haveAllText(*inputFields) }
        }
    }
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
    if (actionText != null && action != null) {
        snackbar.setAction(actionText, action)
    }
    snackbar.show()
}

fun View.fadeIn() {
    animate().apply {
        alpha(1f)
        duration = context.getLong(android.R.integer.config_shortAnimTime)
        interpolator = DecelerateInterpolator()
        start()
    }
}

fun View.fadeOut() {
    animate().apply {
        alpha(0f)
        duration = context.getLong(android.R.integer.config_shortAnimTime)
        interpolator = AccelerateInterpolator()
        start()
    }
}

fun View.animateScaleBounceOut(duration: Long = 150, scale: Float = 1.1f, tension: Float = 6f) {
    animate().apply {
        this.duration = duration
        scaleX(scale)
        scaleY(scale)
        interpolator = AnticipateInterpolator(tension)
        start()
    }
}

fun View.animateScaleBounceIn(duration: Long = 150, scale: Float = 0.91f, tension: Float = 6f) {
    animate().apply {
        this.duration = duration
        scaleX(scale)
        scaleY(scale)
        interpolator = AnticipateInterpolator(tension)
        start()
    }
}

inline fun View.doOnGlobalLayout(crossinline action: () -> Unit) {
    val vto = viewTreeObserver
    vto.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            action()
            when {
                vto.isAlive -> vto.removeOnGlobalLayoutListener(this)
                else -> viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        }
    })
}

fun BottomNavigationView.checkItem(@IdRes itemId: Int) {
    menu.findItem(itemId).isChecked = true
}

fun View.setWidth(width: Int) {
    layoutParams.width = width
    layoutParams = layoutParams
}

fun View.setHeight(height: Int) {
    layoutParams.height = height
    layoutParams = layoutParams
}

fun View.setWidthAndHeight(width: Int, height: Int) {
    layoutParams.width = width
    layoutParams.height = height
    layoutParams = layoutParams
}

fun View.onClick(action: (View) -> Unit) = setOnClickListener(action)

fun View.onLongClick(action: (View) -> Unit) = setOnLongClickListener { action(it); true }

fun View.navigateOnClick(@IdRes resId: Int) = setOnClickListener(Navigation.createNavigateOnClickListener(resId))