package com.example.marvelheroes_amaris.domain.repository

import com.example.marvelheroes_amaris.BaseTest
import com.example.marvelheroes_amaris.domain.api.MarvelInteractor
import com.example.marvelheroes_amaris.domain.models.Hero
import com.example.marvelheroes_amaris.domain.usecase.SingleUseCaseImplementation
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.junit.runners.MethodSorters

@RunWith(JUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class HeroesRepositoryTest : BaseTest() {

    @RelaxedMockK
    private lateinit var interactor: MarvelInteractor

    private lateinit var repository: HeroesRepository

    @Before
    override fun setUp() {
        super.setUp()
        repository = HeroesRepositoryImpl(interactor)

    }

    @Test
    fun t1_when_request_heroes_list_from_repository() {
        val heroesResponse = SingleUseCaseImplementation<List<Hero>>()
        repository.getHeroes(heroesResponse, 0)

        assertNotNull(heroesResponse.data)
    }

    @Test
    fun t2_when_request_hero_from_repository() {
        val heroResponse = SingleUseCaseImplementation<Hero>()
        repository.getHero(heroResponse, "0")

        assertNotNull(heroResponse.data)
    }

}