package com.jueggs.andutils.extension

import android.content.DialogInterface
import android.widget.Toast
import org.jetbrains.anko.browse
import org.jetbrains.anko.longToast
import org.jetbrains.anko.selector

fun androidx.fragment.app.Fragment.longToast(message: CharSequence): Toast? = activity?.longToast(message)

fun androidx.fragment.app.Fragment.longToast(message: Int): Toast? = activity?.longToast(message)

fun androidx.fragment.app.Fragment.browse(url: String, newTask: Boolean = false) = activity?.browse(url, newTask)

fun androidx.fragment.app.Fragment.selector(title: CharSequence? = null, items: List<CharSequence>, onClick: (DialogInterface, Int) -> Unit) = activity?.selector(title, items, onClick)