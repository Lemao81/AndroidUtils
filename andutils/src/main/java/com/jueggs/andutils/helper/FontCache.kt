package com.jueggs.andutils.helper

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import com.jueggs.andutils.Constant.FONT_DIR
import com.log4k.e
import java.io.IOException

class FontCache(private var context: Context) {
    init {
        try {
            val fileList = context.resources.assets.list(FONT_DIR)
            fileList?.forEach { filename ->
                val alias = filename.substring(0, filename.lastIndexOf('.'))
                fontMapping[alias] = filename
                fontMapping[alias.toLowerCase()] = filename
            }
        } catch (ex: IOException) {
            e("Error loading fonts from assets/fonts.", ex)
        }
    }

    fun addFont(name: String, fontFilename: String) {
        fontMapping[name] = fontFilename
    }

    operator fun get(fontName: String): Typeface? {
        val fontFilename = checkNotNull(fontMapping[fontName]) { "Couldn't find font $fontName. Maybe you need to call addFont() first?" }

        return if (cache.containsKey(fontFilename)) {
            cache[fontFilename]
        } else {
            val typeface = Typeface.createFromAsset(context.assets, "$FONT_DIR/$fontFilename")
            cache[fontFilename] = typeface
            typeface
        }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var instance: FontCache? = null
        private val cache = hashMapOf<String, Typeface>()
        private val fontMapping = hashMapOf<String, String>()

        fun getInstance(context: Context): FontCache {
            if (instance == null) instance = FontCache(context.applicationContext)

            return checkNotNull(instance)
        }
    }
}