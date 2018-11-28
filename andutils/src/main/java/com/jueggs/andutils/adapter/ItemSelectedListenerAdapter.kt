package com.jueggs.andutils.adapter

import android.view.View
import android.widget.AdapterView

open class ItemSelectedListenerAdapter : AdapterView.OnItemSelectedListener {
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {}
    override fun onNothingSelected(parent: AdapterView<*>?) {}
}