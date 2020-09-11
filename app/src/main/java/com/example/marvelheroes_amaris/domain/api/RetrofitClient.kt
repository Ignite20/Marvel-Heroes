package com.example.marvelheroes_amaris.domain.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitClient {
    var service: MarvelAPI

    companion object {
        const val BASE_URL: String = "https://gateway.marvel.com"
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(MarvelAPI::class.java)
    }
}