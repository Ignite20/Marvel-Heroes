package com.example.marvelheroes_amaris.domain.usecase

import com.example.marvelheroes_amaris.common.ErrorMessage
import com.example.marvelheroes_amaris.domain.models.Hero

interface HeroesUseCaseContract {
    val heroesResponse: SingleUseCase<List<Hero>>
    val errorResponse: SingleUseCase<ErrorMessage>
    val heroResponse: SingleUseCase<Hero>

    fun getHeroes(offset: Int = 0)

    fun getHero(characterId: String)
}