package com.example.marvelheroes_amaris

import io.mockk.MockKAnnotations
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class BaseTest {

    open fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    fun <R> createCallMock(response: Response<R>): Call<R> {
        return object : Call<R> {
            override fun clone() = this
            override fun execute() = response
            override fun enqueue(callback: Callback<R?>) {}
            override fun isExecuted() = true
            override fun cancel() {}
            override fun isCanceled() = false
            override fun request(): Request {
                return this.request()
            }

            override fun timeout() = Timeout()
        }
    }
}