package com.example.marvelheroes_amaris.ui.heroes_list.usecase

import com.example.marvelheroes_amaris.domain.models.Hero
import com.example.marvelheroes_amaris.domain.repository.HeroesRepository
import com.example.marvelheroes_amaris.domain.usecase.HeroesUseCaseContract
import com.example.marvelheroes_amaris.domain.usecase.SingleUseCaseImplementation

interface HeroesUseCase : HeroesUseCaseContract

class HeroesUseCaseImplementation(var repository: HeroesRepository) : HeroesUseCase {

    override val heroesResponse = SingleUseCaseImplementation<List<Hero>>()
    override val heroResponse = SingleUseCaseImplementation<Hero>()

    override fun getHeroes(offset: Int) {
        repository.getHeroes(heroesResponse, offset)
    }

    override fun getHero(characterId: String) {
        repository.getHero(heroResponse, characterId)
    }

}