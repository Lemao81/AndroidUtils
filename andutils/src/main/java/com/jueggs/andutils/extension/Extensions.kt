package com.jueggs.andutils.extension

import android.animation.ValueAnimator
import android.app.Activity
import android.app.Application
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.annotation.IdRes
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.navigation.NavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.jueggs.andutils.GlideRequest
import com.jueggs.andutils.GlideRequests
import com.jueggs.jutils.usecase.Alter
import com.jueggs.jutils.usecase.StateEvent
import com.jueggs.jutils.usecase.Trigger
import com.jueggs.andutils.helper.ColorAnimator
import com.jueggs.andutils.interfaces.Disposable
import kotlinx.coroutines.channels.SendChannel
import org.apache.commons.validator.routines.EmailValidator
import java.io.ByteArrayOutputStream
import kotlin.reflect.KFunction1

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

suspend fun <TState> SendChannel<StateEvent<TState>>.alter(action: TState.() -> TState) = send(Alter(action))
suspend fun <TState> SendChannel<StateEvent<TState>>.trigger(action: TState.() -> TState) = send(Trigger(action))

val CharSequence?.isValidEmail: Boolean
    get() = !this.isNullOrBlank() && EmailValidator.getInstance().isValid(this.toString())

val CharSequence?.isInvalidEmail: Boolean
    get() = this.isNullOrBlank() || !EmailValidator.getInstance().isValid(this.toString())