package com.example.marvelheroes_amaris.domain.usecase

import com.example.marvelheroes_amaris.domain.models.Hero

interface HeroesUseCaseContract {
    val heroesResponse: SingleUseCase<List<Hero>>

    val heroResponse: SingleUseCase<Hero>

    fun getHeroes(offset: Int = 0)

    fun getHero(characterId: String)
}