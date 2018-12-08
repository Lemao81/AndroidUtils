package com.jueggs.resutils.extension

import androidx.recyclerview.widget.RecyclerView
import com.jueggs.resutils.R
import com.jueggs.resutils.accessory.SimpleDivider

fun RecyclerView.withSimpleDivider() = addItemDecoration(SimpleDivider(context, R.drawable.simple_divider))