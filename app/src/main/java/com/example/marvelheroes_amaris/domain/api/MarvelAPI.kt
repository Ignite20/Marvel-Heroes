package com.example.marvelheroes_amaris.domain.api

import com.example.marvelheroes_amaris.domain.models.MarvelRoot
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelAPI {

    companion object {
        const val TS = "14"
        const val PRIVATE_KEY = "6530af65d5d619e34718e094a28e22e70306ee7a"
        const val PUBLIC_KEY = "4b012176f192fcc1fe00b6b5ca04253e"
    }

    @GET("/v1/public/characters")
    fun getHeroes(
        @Query("apikey") authorization: String? = PUBLIC_KEY,
        @Query("hash") hash: String,
        @Query("ts") ts: String? = TS,
        @Query("offset") offset: Int
    ): Call<MarvelRoot>

    @GET("/v1/public/characters/{characterId}")
    fun getHero(
        @Path("characterId") characterId: String,
        @Query("apikey") authorization: String? = PUBLIC_KEY,
        @Query("hash") hash: String,
        @Query("ts") ts: String? = TS
    ): Call<MarvelRoot>


}