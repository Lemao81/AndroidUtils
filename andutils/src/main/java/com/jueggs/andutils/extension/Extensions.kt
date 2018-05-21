package com.jueggs.andutils.extension

import android.animation.ValueAnimator
import android.app.Activity
import android.arch.lifecycle.*
import android.arch.lifecycle.Observer
import android.databinding.ObservableField
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.*
import com.google.android.gms.common.api.*
import com.jueggs.andutils.*
import com.jueggs.andutils.helper.ColorAnimator
import com.jueggs.andutils.observable.NonNullMediatorLiveData
import java.io.ByteArrayOutputStream
import java.lang.reflect.Field
import java.lang.reflect.Method
import java.util.*
import kotlin.reflect.*

fun Any.findDeclaredMethod(name: String, modifiers: Int = 0x11111111): Method? = javaClass.declaredMethods.singleOrNull { it.name == name && (it.modifiers and modifiers) != 0 }

fun Any.findDeclaredField(name: String, modifiers: Int = 0x11111111): Field? = javaClass.declaredFields.singleOrNull { it.name == name && (it.modifiers and modifiers) != 0 }

fun ApiException.startResolutionForResult(activity: Activity, requestCode: Int) {
    if (this !is ResolvableApiException) return
    startResolutionForResult(activity, requestCode)
}

fun Bitmap.toByteArray(format: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG): ByteArray {
    val baos = ByteArrayOutputStream()
    compress(format, 100, baos)
    return baos.toByteArray()
}

fun GlideRequests.loadOrDefault(url: String?, defaultResId: Int): GlideRequest<Drawable> {
    return if (!url.isNullOrEmpty()) load(url)
    else load(defaultResId)
}

fun GlideRequests.loadOrDefault(url: String?, default: Drawable): GlideRequest<Drawable> {
    return if (!url.isNullOrEmpty()) load(url)
    else load(default)
}

fun ColorAnimator.update(func: KFunction1<Int, Unit>): ValueAnimator {
    valueAnimator.addUpdateListener {
        func(it.animatedValue as Int)
    }
    return valueAnimator
}

fun ValueAnimator.startDelayed(delay: Long) {
    startDelay = delay
    start()
}

fun <T> LiveData<T>.nonNull(): NonNullMediatorLiveData<T> {
    val mediator = NonNullMediatorLiveData<T>()
    mediator.addSource(this, { it?.let { mediator.value = it } })
    return mediator
}

fun <T> NonNullMediatorLiveData<T>.observe(owner: LifecycleOwner, observer: (t: T) -> Unit) = this.observe(owner, Observer { it?.let(observer) })

val Calendar.year: Int
    get() = get(Calendar.YEAR)

val Calendar.month: Int
    get() = get(Calendar.MONTH)

val Calendar.dayOfMonth: Int
    get() = get(Calendar.DAY_OF_MONTH)

val Date.unix: Long
    get() = time / 1000

fun <T> ObservableField<T>.getOr(default: T): T = get() ?: default

fun MutableLiveData<Unit>.fire() {
    value = Unit
}