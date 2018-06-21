package com.jueggs.andutils.extension

import android.arch.lifecycle.*
import com.jueggs.andutils.observable.NonNullMediatorLiveData

fun <T> LiveData<T>.nonNull(): NonNullMediatorLiveData<T> {
    val mediator = NonNullMediatorLiveData<T>()
    mediator.addSource(this, { it?.let { mediator.value = it } })
    return mediator
}

fun <T> NonNullMediatorLiveData<T>.observe(owner: LifecycleOwner, observer: (T) -> Unit) = this.observe(owner, Observer { it?.let(observer) })

fun MutableLiveData<Unit>.fire() {
    value = Unit
}

fun MutableLiveData<Unit>.post() = postValue(Unit)

fun MutableLiveData<Boolean>.fire(value: Boolean?) {
    this.value = value
}

fun MutableLiveData<Boolean>.post(value: Boolean?) = postValue(value)

fun MutableLiveData<Boolean>.fireTrue() {
    value = true
}

fun MutableLiveData<Boolean>.postTrue() = postValue(true)

fun MutableLiveData<Boolean>.fireFalse() {
    value = false
}

fun MutableLiveData<Boolean>.postFalse() = postValue(false)

fun MutableLiveData<Int>.fire(id: Int?) {
    value = id
}

fun MutableLiveData<Int>.post(id: Int?) = postValue(id)