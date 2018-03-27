package com.jueggs.andutils.observable

import com.jueggs.andutils.extension.check
import io.reactivex.*
import io.reactivex.disposables.Disposable

class CheckObserver<T>(private val actual: Observer<in T>, private val predicate: (T) -> Boolean) : Observer<T>, Disposable {
    private var disposable: Disposable? = null

    override fun onComplete() = actual.onComplete()

    override fun onSubscribe(d: Disposable) {
        disposable = d
        actual.onSubscribe(this)
    }

    override fun onNext(t: T) {
        if (predicate(t)) actual.onNext(t)
        else actual.onError(Exception("${Observable::class.simpleName}.${Observable<T>::check.name} condition failed"))
    }

    override fun onError(e: Throwable) = actual.onError(e)

    override fun isDisposed()= disposable != null && disposable!!.isDisposed

    override fun dispose() {
        if (disposable != null) disposable!!.dispose()
    }
}