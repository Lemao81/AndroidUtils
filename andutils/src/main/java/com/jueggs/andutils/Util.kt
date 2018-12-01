package com.jueggs.andutils

import android.os.Handler
import android.support.v4.view.ViewCompat
import android.view.View
import android.widget.EditText
import androidx.core.os.postDelayed
import androidx.core.util.toAndroidPair
import com.jueggs.andutils.aac.StateEvent
import com.jueggs.andutils.util.Action
import com.jueggs.jutils.pairOf
import io.reactivex.Observable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import java.lang.IllegalStateException
import java.security.InvalidParameterException

object Util {
    fun createSharedElement(view: View, transitionName: String): android.util.Pair<View, String> = pairOf(view, transitionName).toAndroidPair()

    fun viewsVisibleIf(condition: Boolean, vararg views: View) = views.forEach { if (condition) it.visibility = View.VISIBLE else it.visibility = View.GONE }

    fun viewsGoneIf(condition: Boolean, vararg views: View) = views.forEach { if (condition) it.visibility = View.GONE else it.visibility = View.VISIBLE }

    inline fun <reified T> checkCast(obj: Any) {
        if (!T::class.java.isAssignableFrom(obj::class.java)) throw TypeCastException("class ${obj::class.java.simpleName} must be assignable from ${T::class.java.simpleName}")
    }

    fun hasText(vararg inputFields: EditText): Boolean = inputFields.all { !it.text.isNullOrEmpty() }

    inline fun postDelayed(delayInMillis: Long, token: Any? = null, crossinline action: () -> Unit): Runnable = Handler().postDelayed(delayInMillis, token, action)

    fun isVerticalScroll(axes: Int) = axes == ViewCompat.SCROLL_AXIS_VERTICAL

    fun isHorizontalScroll(axes: Int) = axes == ViewCompat.SCROLL_AXIS_HORIZONTAL

    @ExperimentalCoroutinesApi
    fun <T> produceActions(f: suspend ProducerScope<Action<T>>.() -> Unit): ReceiveChannel<Action<T>> = GlobalScope.produce(block = f)

    @ExperimentalCoroutinesApi
    fun <T> produceEvents(f: suspend ProducerScope<StateEvent<T>>.() -> Unit): ReceiveChannel<StateEvent<T>> = GlobalScope.produce(block = f)

    fun <T> mergeObservables(vararg observables: Observable<T>): Observable<T> {
        return if (observables.size > 4) {
            val merge = Observable.merge(observables[0], observables[1], observables[2], observables[3])
            val reduced = observables.slice(IntRange(4, observables.size - 1)).toMutableList()
            reduced.add(merge)
            mergeObservables(*reduced.toTypedArray())
        } else when (observables.size) {
            0 -> throw InvalidParameterException("No observables to merge")
            1 -> observables[0]
            2 -> Observable.merge(observables[0], observables[1])
            3 -> Observable.merge(observables[0], observables[1], observables[2])
            4 -> Observable.merge(observables[0], observables[1], observables[2], observables[3])
            else -> throw IllegalStateException()
        }
    }
}