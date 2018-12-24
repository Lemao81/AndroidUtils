package com.jueggs.andutils.adapter

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class TextWatcherImpl : TextWatcher {
    private var afterChangedFunc: ((Editable?) -> Unit)? = null
    private var beforeChangedFunc: ((CharSequence?, Int, Int, Int) -> Unit)? = null
    private var onChangedFunc: ((CharSequence?, Int, Int, Int) -> Unit)? = null

    fun _afterTextChanged(func: ((Editable?) -> Unit)) {
        afterChangedFunc = func
    }

    fun _beforeTextChanged(func: ((CharSequence?, Int, Int, Int) -> Unit)) {
        beforeChangedFunc = func
    }

    fun _onTextChanged(func: ((CharSequence?, Int, Int, Int) -> Unit)) {
        onChangedFunc = func
    }

    override fun afterTextChanged(s: Editable?) = afterChangedFunc?.invoke(s) ?: Unit

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = beforeChangedFunc?.invoke(s, start, count, after) ?: Unit

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = onChangedFunc?.invoke(s, start, before, count) ?: Unit
}

fun EditText._addTextChangedListener(block: TextWatcherImpl.() -> Unit) = addTextChangedListener(TextWatcherImpl().apply(block))