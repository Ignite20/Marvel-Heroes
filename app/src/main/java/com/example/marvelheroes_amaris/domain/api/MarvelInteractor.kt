package com.example.marvelheroes_amaris.domain.api

import com.example.marvelheroes_amaris.common.ErrorSomethingWentWrong
import com.example.marvelheroes_amaris.common.calculateMD5
import com.example.marvelheroes_amaris.domain.models.MarvelRoot
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MarvelInteractor(val retrofitClient: RetrofitClient) {

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
                callback.onHeroesListKO(ErrorSomethingWentWrong)
            }
        })
    }

    fun getHero(callback: MarvelCallback, characterId: String) {

        retrofitClient.service.getHero(
            hash = calculateMarvelMD5(),
            characterId = characterId
        ).enqueue(object : Callback<MarvelRoot> {
            override fun onResponse(call: Call<MarvelRoot>, response: Response<MarvelRoot>) {
                if (response.isSuccessful) {
                    response.body()?.data?.let {
                        callback.onHeroOK(it.results.first())
                    }
                }
            }

            override fun onFailure(call: Call<MarvelRoot>, t: Throwable) {
                callback.onHeroKO(ErrorSomethingWentWrong)
            }
        })


    }

    private fun calculateMarvelMD5() =
        calculateMD5(MarvelAPI.TS, MarvelAPI.PRIVATE_KEY, MarvelAPI.PUBLIC_KEY)
}