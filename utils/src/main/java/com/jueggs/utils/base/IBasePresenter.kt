package com.jueggs.utils.base

import android.content.Intent

interface IBasePresenter {
    fun onResult(request: Int, result: Int, data: Intent?) {
        TODO("not implemented")
    }
}