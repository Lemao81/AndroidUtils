package com.jueggs.andutils.delegate

import android.view.View
import com.jueggs.andutils.interfaces.Identifiable

class ViewIdIdentifier : Identifiable {
    private var _id = lazy { View.generateViewId().toLong() }
    override val id = _id.value
}