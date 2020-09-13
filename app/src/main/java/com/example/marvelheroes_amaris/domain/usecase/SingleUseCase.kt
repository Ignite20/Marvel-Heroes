package com.example.marvelheroes_amaris.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.marvelheroes_amaris.common.ErrorMessage
import com.example.marvelheroes_amaris.common.ErrorSomethingWentWrong

interface SingleUseCase<T> {

    val data: LiveData<T>
    val error: LiveData<ErrorMessage>
}

class SingleUseCaseImplementation<T> : ApiCallback<T, ErrorMessage>, SingleUseCase<T> {

    private val _error: MutableLiveData<ErrorMessage> = MutableLiveData()

    private val _data: MutableLiveData<T> = MutableLiveData()

    override val data: LiveData<T> = _data

    override val error: LiveData<ErrorMessage> = _error

    override fun onResponse(response: T?) {
        if (response != null) {
            _data.postValue(response)
        } else {
            _error.postValue(ErrorSomethingWentWrong)
        }
    }

    override fun onErrorResponse(response: ErrorMessage?) {
        _error.postValue(response ?: ErrorSomethingWentWrong)
    }


}