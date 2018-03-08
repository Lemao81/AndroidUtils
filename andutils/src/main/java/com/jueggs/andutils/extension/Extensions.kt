package com.jueggs.andutils.extension

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.google.android.gms.common.api.*
import com.jueggs.andutils.*
import java.io.ByteArrayOutputStream
import java.lang.reflect.Field
import java.lang.reflect.Method

fun Any.findDeclaredMethod(name: String, modifiers: Int = 0x11111111): Method? = javaClass.declaredMethods.singleOrNull { it.name == name && (it.modifiers and modifiers) != 0 }

fun Any.findDeclaredField(name: String, modifiers: Int = 0x11111111): Field? = javaClass.declaredFields.singleOrNull { it.name == name && (it.modifiers and modifiers) != 0 }

fun ApiException.startResolutionForResult(activity: Activity, requestCode: Int) {
    if (this !is ResolvableApiException) return
    startResolutionForResult(activity, requestCode)
}

fun Bitmap.toByteArray(format: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG): ByteArray {
    val baos = ByteArrayOutputStream()
    compress(Bitmap.CompressFormat.JPEG, 100, baos)
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