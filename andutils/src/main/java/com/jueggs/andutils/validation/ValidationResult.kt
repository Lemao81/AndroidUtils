package com.jueggs.andutils.validation

import androidx.annotation.StringRes

sealed class ValidationResult

object Valid : ValidationResult()
data class Invalid(@StringRes val resId: Int) : ValidationResult()