package com.jueggs.andutils.adapter

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class TextWatcherImpl : TextWatcher {
    private var afterChanged: ((Editable?) -> Unit)? = null
    private var beforeChanged: ((CharSequence?, Int, Int, Int) -> Unit)? = null
    private var onChanged: ((CharSequence?, Int, Int, Int) -> Unit)? = null

    fun _afterTextChanged(func: ((Editable?) -> Unit)) {
        afterChanged = func
    }

    fun _beforeTextChanged(func: ((CharSequence?, Int, Int, Int) -> Unit)) {
        beforeChanged = func
    }

    fun _onTextChanged(func: ((CharSequence?, Int, Int, Int) -> Unit)) {
        onChanged = func
    }

    override fun afterTextChanged(s: Editable?) = afterChanged?.invoke(s) ?: Unit

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = beforeChanged?.invoke(s, start, count, after) ?: Unit

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = onChanged?.invoke(s, start, before, count) ?: Unit
}

fun EditText._addTextChangedListener(block: TextWatcherImpl.() -> Unit) = addTextChangedListener(TextWatcherImpl().apply(block))