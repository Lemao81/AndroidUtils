package com.jueggs.andutils.extension

import android.view.inputmethod.EditorInfo
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

fun TextView.asString(): String = text.toString()

fun TextView.asInt(): Int = text.toString().toInt()

fun TextView.onEditDone(
    context: CoroutineContext = Dispatchers.Main,
    handler: suspend CoroutineScope.() -> Unit
) {
    setOnEditorActionListener { view, actionId, event ->
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            GlobalScope.launch(context, CoroutineStart.DEFAULT) {
                handler()
            }

            return@setOnEditorActionListener true
        }

        return@setOnEditorActionListener false
    }
}