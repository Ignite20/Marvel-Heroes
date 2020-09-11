package com.example.marvelheroes_amaris.domain.api

import com.example.marvelheroes_amaris.domain.models.MarvelRoot
import com.example.marvelheroes_amaris.utils.md5
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MarvelInteractor {
    private var retrofitClient = RetrofitClient()

    fun getHeroes(callback: MarvelCallback, offset: Int = 0) {
        retrofitClient.service.getHeroes(
            hash = calculateMarvelMD5(),
            offset = offset
        ).enqueue(object : Callback<MarvelRoot> {
            override fun onResponse(call: Call<MarvelRoot>, response: Response<MarvelRoot>) {
                response.body()?.data?.let {
                    callback.onHeroesListOK(it.results.toMutableList())
                }
            }

            override fun onFailure(call: Call<MarvelRoot>, t: Throwable) {
                callback.onHeroesListKO()
            }
        })
    }

    fun getHero(callback: MarvelCallback, characterId: String) {
        val response = retrofitClient.service.getHero(
            hash = calculateMarvelMD5(),
            characterId = characterId
        ).execute()

        if (response.isSuccessful) {
            response.body()?.data?.let {
                it.results[0]
            }
        }
    }

    private fun calculateMarvelMD5(): String {
        val stringBuilder = StringBuilder(
            MarvelAPI.TS.plus(MarvelAPI.PRIVATE_KEY).plus(MarvelAPI.PUBLIC_KEY)
        )
        return stringBuilder.toString().md5()
    }
}