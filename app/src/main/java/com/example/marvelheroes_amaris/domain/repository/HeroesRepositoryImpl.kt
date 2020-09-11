package com.example.marvelheroes_amaris.domain.repository

import com.example.marvelheroes_amaris.domain.api.MarvelCallback
import com.example.marvelheroes_amaris.domain.api.MarvelInteractor
import com.example.marvelheroes_amaris.domain.models.Hero
import com.example.marvelheroes_amaris.domain.usecase.ApiCallback

class HeroesRepositoryImpl : HeroesRepository {

    private val marvelInteractor = MarvelInteractor()

    override fun getHeroes(callback: ApiCallback<List<Hero>, Error>, offset: Int) {
        marvelInteractor.getHeroes(object : MarvelCallback {

            override fun onHeroesListOK(list: MutableList<Hero>) {
                callback.onResponse(list)
            }

            override fun onHeroesListKO() {
                callback.onErrorResponse(null)
            }

            override fun onHeroKO() {
                // Not used
            }

            override fun onHeroOK(hero: Hero) {
                // Not used
            }

        }, offset)
    }

    override fun getHero(callback: ApiCallback<Hero, Error>, characterId: String) {
        marvelInteractor.getHero(object : MarvelCallback {
            override fun onHeroesListOK(list: MutableList<Hero>) {
                // Not used
            }

            override fun onHeroesListKO() {
                // Not used
            }

            override fun onHeroKO() {
                callback.onErrorResponse(null)
            }

            override fun onHeroOK(hero: Hero) {
                callback.onResponse(hero)
            }

        }, characterId)
    }
}