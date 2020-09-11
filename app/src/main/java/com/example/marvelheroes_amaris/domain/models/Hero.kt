package com.example.marvelheroes_amaris.domain.models

data class Hero(
    val id: String,
    val name: String,
    val description: String,
    val modified: String,
    val thumbnail: Thumbnail,
    val resourceURI: String
)