package com.example.marvelheroes_amaris.common

import androidx.lifecycle.MediatorLiveData

class StateLiveData<T> : MediatorLiveData<T>() {

    fun postError(value: T) {
        super.postValue(value)
    }
}