package com.example.marvelheroes_amaris.domain.api

import com.example.marvelheroes_amaris.common.ErrorMessage
import com.example.marvelheroes_amaris.domain.models.Hero

interface MarvelCallback {
    fun onHeroesListOK(list: MutableList<Hero>)
    fun onHeroesListKO(message: ErrorMessage?)
    fun onHeroOK(hero: Hero)
    fun onHeroKO(message: ErrorMessage?)
}