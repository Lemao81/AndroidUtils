package com.jueggs.andutils.usecase

interface Validator<TData> {
    suspend fun validate(data: TData): ValidationResult
}