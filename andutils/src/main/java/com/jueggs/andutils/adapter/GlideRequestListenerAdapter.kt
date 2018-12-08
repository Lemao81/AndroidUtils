package com.jueggs.andutils.adapter

import android.graphics.drawable.Drawable
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class GlideRequestListenerAdapter(
    private val onSuccess: (Drawable) -> Unit = {},
    private val onFail: (Exception?) -> Unit = {},
    private val onComplete: () -> Unit = {}
) : RequestListener<Drawable> {
    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
        if (resource != null) onSuccess(resource)
        onComplete()
        return false
    }

    override fun onLoadFailed(exception: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
        onFail(exception)
        onComplete()
        return false
    }
}

class GlideRequestListenerImpl : RequestListener<Drawable> {
    private var loadFaild: ((GlideException?, Any?, Target<Drawable>?, Boolean) -> Boolean)? = null
    private var resourceReady: ((Drawable?, Any?, Target<Drawable>?, DataSource?, Boolean) -> Boolean)? = null

    fun _onLoadFailed(func: ((GlideException?, Any?, Target<Drawable>?, Boolean) -> Boolean)) {
        loadFaild = func
    }

    fun _onResourceReady(func: ((Drawable?, Any?, Target<Drawable>?, DataSource?, Boolean) -> Boolean)) {
        resourceReady = func
    }

    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean = loadFaild?.invoke(e, model, target, isFirstResource) ?: false

    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean =
        resourceReady?.invoke(resource, model, target, dataSource, isFirstResource) ?: false
}