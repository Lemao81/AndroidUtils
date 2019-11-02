package com.jueggs.andutils.extension

import android.content.DialogInterface
import android.widget.Toast
import org.jetbrains.anko.browse
import org.jetbrains.anko.longToast
import org.jetbrains.anko.selector
import org.jetbrains.anko.toast

fun androidx.fragment.app.Fragment.shortToast(message: CharSequence): Toast = requireActivity().toast(message)

fun androidx.fragment.app.Fragment.shortToast(message: Int): Toast = requireActivity().toast(message)

fun androidx.fragment.app.Fragment.longToast(message: CharSequence): Toast = requireActivity().longToast(message)

fun androidx.fragment.app.Fragment.longToast(message: Int): Toast = requireActivity().longToast(message)

fun androidx.fragment.app.Fragment.browse(url: String, newTask: Boolean = false) = requireActivity().browse(url, newTask)

fun androidx.fragment.app.Fragment.selector(title: CharSequence? = null, items: List<CharSequence>, onClick: (DialogInterface, Int) -> Unit) = requireActivity().selector(title, items, onClick)