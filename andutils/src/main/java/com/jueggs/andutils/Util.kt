package com.jueggs.andutils

import android.os.Handler
import android.view.View
import android.widget.EditText
import androidx.core.os.postDelayed
import androidx.core.util.toAndroidPair
import androidx.core.view.ViewCompat
import com.jueggs.jutils.helper.Action
import com.jueggs.jutils.pairOf
import com.jueggs.jutils.usecase.StateEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce

object Util {
    fun createSharedElement(view: View, transitionName: String): android.util.Pair<View, String> = pairOf(view, transitionName).toAndroidPair()

    fun viewsVisibleIf(condition: Boolean, vararg views: View) = views.forEach { if (condition) it.visibility = View.VISIBLE else it.visibility = View.GONE }

    fun viewsGoneIf(condition: Boolean, vararg views: View) = views.forEach { if (condition) it.visibility = View.GONE else it.visibility = View.VISIBLE }

    inline fun <reified T> checkCast(obj: Any) {
        if (!T::class.java.isAssignableFrom(obj::class.java)) throw TypeCastException("class ${obj::class.java.simpleName} must be assignable from ${T::class.java.simpleName}")
    }

    fun haveAllText(vararg inputFields: EditText): Boolean = inputFields.all { !it.text.isNullOrEmpty() }

    inline fun doWithDelay(delayInMillis: Long, token: Any? = null, crossinline action: () -> Unit): Runnable = Handler().postDelayed(delayInMillis, token, action)

    fun isVerticalScroll(axes: Int) = axes == ViewCompat.SCROLL_AXIS_VERTICAL

    fun isHorizontalScroll(axes: Int) = axes == ViewCompat.SCROLL_AXIS_HORIZONTAL

    @ExperimentalCoroutinesApi
    fun <T> CoroutineScope.produceActions(f: suspend ProducerScope<Action<T>>.() -> Unit): ReceiveChannel<Action<T>> = produce(block = f)

    @ExperimentalCoroutinesApi
    fun <T> CoroutineScope.produceEvents(f: suspend ProducerScope<StateEvent<T>>.() -> Unit): ReceiveChannel<StateEvent<T>> = produce(block = f)
}