package com.jueggs.utils.extension

import com.jueggs.andutils.util.logDebug
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Observable<T>.scheduleIoMain(): Observable<T> = subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

fun <T> Observable<T>.logError(): Observable<T> = doOnError { logDebug(it.message) }

fun <T> Observable<T>.check(predicate: (T) -> Boolean): Observable<Boolean> = Observable.create({ emitter ->
    subscribe {
        if (predicate(it)) emitter.onNext(true)
        else emitter.onError(Exception("${Observable::class.simpleName}.${Observable<T>::check.name} condition failed"))
        emitter.onComplete()
    }
})

fun <T> Observable<T>.checkWith(predicate: (T) -> Boolean): Observable<T> = Observable.create({ emitter ->
    subscribe {
        if (predicate(it)) emitter.onNext(it)
        else emitter.onError(Exception("${Observable::class.simpleName}.${Observable<T>::check.name} condition failed"))
        emitter.onComplete()
    }
})

fun <T> Single<T>.check(predicate: (T) -> Boolean): Single<Boolean> = Single.create({ emitter ->
    subscribe { t ->
        if (predicate(t)) emitter.onSuccess(true)
        else emitter.onError(Exception("${Observable::class.simpleName}.${Observable<T>::check.name} condition failed"))
    }
})

fun <T> Single<T>.checkWith(predicate: (T) -> Boolean): Single<T> = Single.create({ emitter ->
    subscribe { t ->
        if (predicate(t)) emitter.onSuccess(t)
        else emitter.onError(Exception("${Observable::class.simpleName}.${Observable<T>::check.name} condition failed"))
    }
})