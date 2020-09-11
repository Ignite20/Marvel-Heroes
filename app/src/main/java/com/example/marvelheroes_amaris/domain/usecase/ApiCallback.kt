package com.example.marvelheroes_amaris.domain.usecase

interface ApiCallback<S, E> {
    /**
     * Invoked for a received HTTP response.
     */
    fun onResponse(response: S?)


    /**
     * Invoked for a received HTTP error response.
     */
    fun onErrorResponse(response: E?)

}