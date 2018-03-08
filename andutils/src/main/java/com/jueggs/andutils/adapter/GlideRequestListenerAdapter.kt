package com.jueggs.andutils.adapter

import android.graphics.drawable.Drawable
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class GlideRequestListenerAdapter(
        private val onSuccess: (Drawable) -> Unit = {},
        private val onFail: (Exception?) -> Unit = {},
        private val onComplete: () -> Unit = {}) : RequestListener<Drawable> {
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