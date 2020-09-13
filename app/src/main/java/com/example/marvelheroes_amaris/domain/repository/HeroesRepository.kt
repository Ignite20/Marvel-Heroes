package com.example.marvelheroes_amaris.domain.repository

import com.example.marvelheroes_amaris.common.ErrorMessage
import com.example.marvelheroes_amaris.domain.models.Hero
import com.example.marvelheroes_amaris.domain.usecase.ApiCallback

interface HeroesRepository {

    fun getHeroes(callback: ApiCallback<List<Hero>, ErrorMessage>, offset: Int = 0)

    fun getHero(callback: ApiCallback<Hero, ErrorMessage>, characterId: String)

}