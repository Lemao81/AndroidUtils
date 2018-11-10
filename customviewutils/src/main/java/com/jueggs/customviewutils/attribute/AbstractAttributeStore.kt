package com.jueggs.customviewutils.attribute

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.core.content.withStyledAttributes

abstract class AbstractAttributeStore<T : Any> {
    lateinit var attributes: T

    protected fun init(context: Context, attrs: AttributeSet?, stylableResId: IntArray) {
        this.attributes = createAttributes(context)
        attrs?.let { context.withStyledAttributes(it, stylableResId, block = setAttributes(context, attributes)) }
    }

    abstract fun createAttributes(context: Context): T
    abstract fun setAttributes(context: Context, attributes: T): TypedArray.() -> Unit
}