package com.jueggs.andutils.observable

import io.reactivex.Observable
import io.reactivex.Observer

class CheckObservable<T>(private val source: Observable<T>, private val predicate: (T) -> Boolean) : Observable<T>() {
    override fun subscribeActual(observer: Observer<in T>) {
        source.subscribe(CheckObserver<T>(observer, predicate))
    }
}