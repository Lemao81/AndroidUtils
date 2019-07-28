package com.jueggs.rxutils.observable

import io.reactivex.Single
import io.reactivex.SingleObserver

class CheckSingle<T>(private val source: Single<T>, private val predicate: (T) -> Boolean) : Single<T>() {
    override fun subscribeActual(observer: SingleObserver<in T>) {
        source.subscribe(CheckSingleObserver<T>(observer, predicate))
    }
}