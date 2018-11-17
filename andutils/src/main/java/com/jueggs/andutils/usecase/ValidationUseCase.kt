package com.jueggs.andutils.usecase

interface ValidationUseCase<TData> {
    suspend fun validate(data: TData): ValidationResult
}