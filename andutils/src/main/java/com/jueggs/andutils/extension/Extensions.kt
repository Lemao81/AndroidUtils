package com.jueggs.andutils.extension

import android.animation.ValueAnimator
import android.app.*
import android.arch.lifecycle.*
import android.databinding.ObservableField
import android.graphics.Bitmap
import android.graphics.drawable.*
import android.support.annotation.IdRes
import android.support.constraint.ConstraintLayout
import android.support.design.widget.CoordinatorLayout
import android.support.v7.widget.Toolbar
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.NavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.common.api.*
import com.jueggs.andutils.*
import com.jueggs.andutils.helper.ColorAnimator
import com.jueggs.andutils.interfaces.Disposable
import kotlinx.coroutines.channels.SendChannel
import java.io.ByteArrayOutputStream
import kotlin.reflect.*

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

fun ColorAnimator.update(func: KFunction1<Int, Unit>): ValueAnimator = valueAnimator.also { it.addUpdateListener { func(it.animatedValue as Int) } }

fun ValueAnimator.startDelayed(delay: Long) {
    startDelay = delay
    start()
}

fun <T> ObservableField<T>.getOr(default: T): T = get() ?: default

inline fun <T : Disposable?, R> T.use(block: (T) -> R): R {
    var exception: Throwable? = null
    try {
        return block(this)
    } catch (e: Throwable) {
        exception = e
        throw e
    } finally {
        when {
            this == null -> {
            }
            exception == null -> dispose()
            else ->
                try {
                    dispose()
                } catch (disposeException: Throwable) {
                    // cause.addSuppressed(disposeException) // ignored here
                }
        }
    }
}

fun <TApplication : Application> AndroidViewModel.doWithNetworkConnection(action: () -> Unit): () -> Boolean = getApplication<TApplication>().doWithNetworkConnection(action)

infix fun (() -> Boolean).otherwise(otherwiseAction: () -> Unit) {
    val condition = this
    if (!condition())
        otherwiseAction()
}

fun Drawable.asShapeDrawable(): ShapeDrawable = this as ShapeDrawable
fun Drawable.asGradientDrawable(): GradientDrawable = this as GradientDrawable
fun Drawable.asBitmapDrawable(): BitmapDrawable = this as BitmapDrawable
fun Drawable.asColorDrawable(): ColorDrawable = this as ColorDrawable

fun ViewGroup.LayoutParams.asMarginLayoutParams(): ViewGroup.MarginLayoutParams = this as ViewGroup.MarginLayoutParams
fun ViewGroup.LayoutParams.asCoordinatorLayoutParams(): CoordinatorLayout.LayoutParams = this as CoordinatorLayout.LayoutParams
fun ViewGroup.LayoutParams.asConstraintLayoutParams(): ConstraintLayout.LayoutParams = this as ConstraintLayout.LayoutParams
fun ViewGroup.LayoutParams.asFrameLayoutParams(): FrameLayout.LayoutParams = this as FrameLayout.LayoutParams
fun ViewGroup.LayoutParams.asLinearLayoutParams(): LinearLayout.LayoutParams = this as LinearLayout.LayoutParams

fun NavController?.setupWithToolbar(activity: Activity?, @IdRes toolbarId: Int) {
    val toolbar = activity?.findViewById<Toolbar>(toolbarId)
    this?.let { toolbar?.setupWithNavController(it) }
}

suspend fun <T> SendChannel<T>.sendAndClose(element: T) {
    send(element)
    close()
}