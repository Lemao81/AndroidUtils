package com.jueggs.customviewutils.attribute

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.core.content.withStyledAttributes
import com.log4k.e

abstract class AbstractAttributeStore<T : Any>(context: Context, attrs: AttributeSet?, stylableResId: IntArray) {
    val attributes: T by lazy {
        val result = createAttributes(context.applicationContext)
        attrs?.let { context.applicationContext.withStyledAttributes(it, stylableResId, block = initializeAttributes(context.applicationContext, result)) }

        try {
            with(result, validateAttributes())
        } catch (ex: Exception) {
            e(ex.message ?: "", ex)
            throw ex
        }

        result
    }

    abstract fun createAttributes(context: Context): T
    abstract fun initializeAttributes(context: Context, attributes: T): TypedArray.() -> Unit
    open fun validateAttributes(): T.() -> Unit = {}
}