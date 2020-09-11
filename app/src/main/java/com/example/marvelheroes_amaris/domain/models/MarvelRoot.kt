package com.example.marvelheroes_amaris.domain.models

data class MarvelRoot(
    val data: MarvelData
)

data class MarvelData(
    val total: Int,
    val results: List<Hero>
)