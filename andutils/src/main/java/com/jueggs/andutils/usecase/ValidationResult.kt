package com.jueggs.andutils.usecase

import android.support.annotation.StringRes

sealed class ValidationResult

object Valid : ValidationResult()

data class Invalid(@StringRes val resId: Int) : ValidationResult()