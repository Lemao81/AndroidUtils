package com.jueggs.andutils

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView

open class TextWatcherAdapter : TextWatcher {
    override fun afterTextChanged(text: Editable?) {}
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
}

open class ItemSelectedListenerAdapter : AdapterView.OnItemSelectedListener {
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {}
    override fun onNothingSelected(parent: AdapterView<*>?) {}
}