package com.jueggs.andutils.helper

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import com.jueggs.andutils.*
import com.jueggs.andutils.util.logTagged
import java.io.IOException

class FontCache(private var context: Context) {
    init {
        try {
            val fileList = context.resources.assets.list(FONT_DIR)
            fileList.forEach { filename ->
                val alias = filename.substring(0, filename.lastIndexOf('.'))
                fontMapping.put(alias, filename)
                fontMapping.put(alias.toLowerCase(), filename)
            }
        } catch (e: IOException) {
            logTagged(TAG_FONT_CACHE, LOG_LEVEL_ERROR, "Error loading fonts from assets/fonts.")
        }
    }

    fun addFont(name: String, fontFilename: String) {
        fontMapping.put(name, fontFilename)
    }

    operator fun get(fontName: String): Typeface? {
        val fontFilename = fontMapping.get(fontName)
        if (fontFilename.isNullOrEmpty()) {
            logTagged(TAG_FONT_CACHE, LOG_LEVEL_ERROR, "Couldn't find font $fontName. Maybe you need to call addFont() first?")
            return null
        }

        return if (cache.containsKey(fontFilename)) {
            cache[fontFilename]
        } else {
            val typeface = Typeface.createFromAsset(context.assets, "${FONT_DIR}/$fontFilename")
            cache[fontFilename!!] = typeface
            typeface
        }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var instance: FontCache? = null
        private val cache = hashMapOf<String, Typeface>()
        private val fontMapping = hashMapOf<String, String>()

        fun getInstance(context: Context): FontCache {
            if (instance == null)
                instance = FontCache(context.applicationContext)
            return instance!!
        }
    }
}