package com.example.marvelheroes_amaris.domain.repository

import com.example.marvelheroes_amaris.common.ErrorMessage
import com.example.marvelheroes_amaris.domain.api.MarvelCallback
import com.example.marvelheroes_amaris.domain.api.MarvelInteractor
import com.example.marvelheroes_amaris.domain.models.Hero
import com.example.marvelheroes_amaris.domain.usecase.ApiCallback

class HeroesRepositoryImpl(val marvelInteractor: MarvelInteractor) : HeroesRepository {

    //private val marvelInteractor = MarvelInteractor()

    override fun getHeroes(callback: ApiCallback<List<Hero>, ErrorMessage>, offset: Int) {
        marvelInteractor.getHeroes(object : MarvelCallback {

            override fun onHeroesListOK(list: MutableList<Hero>) {
                callback.onResponse(list)
            }

            override fun onHeroesListKO(message: ErrorMessage?) {
                callback.onErrorResponse(message)
            }

            override fun onHeroKO(message: ErrorMessage?) {
                // Not used
            }

            override fun onHeroOK(hero: Hero) {
                // Not used
            }

        }, offset)
    }

    override fun getHero(callback: ApiCallback<Hero, ErrorMessage>, characterId: String) {
        marvelInteractor.getHero(object : MarvelCallback {
            override fun onHeroesListOK(list: MutableList<Hero>) {
                // Not used
            }

            override fun onHeroesListKO(message: ErrorMessage?) {
                // Not used
            }

            override fun onHeroKO(message: ErrorMessage?) {
                callback.onErrorResponse(message)
            }

            override fun onHeroOK(hero: Hero) {
                callback.onResponse(hero)
            }

        }, characterId)
    }
}