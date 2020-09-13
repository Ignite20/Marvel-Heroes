package com.example.marvelheroes_amaris.domain.api

import com.example.marvelheroes_amaris.BaseTest
import com.example.marvelheroes_amaris.common.ErrorMessage
import com.example.marvelheroes_amaris.common.calculateMD5
import com.example.marvelheroes_amaris.domain.models.Hero
import com.example.marvelheroes_amaris.domain.models.MarvelData
import com.example.marvelheroes_amaris.domain.models.MarvelRoot
import com.example.marvelheroes_amaris.domain.models.Thumbnail
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.junit.runners.MethodSorters
import retrofit2.Call
import retrofit2.Response


@RunWith(JUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
internal class MarvelInteractorTest : BaseTest() {


    @RelaxedMockK
    lateinit var retrofitClient: RetrofitClient

    @RelaxedMockK
    lateinit var service: MarvelAPI

    private lateinit var interactor: MarvelInteractor

    @Before
    override fun setUp() {
        super.setUp()
        interactor = MarvelInteractor(retrofitClient)
        mockResponses()
    }

    private fun mockResponses() {

        val bodyResponse = MarvelRoot(
            MarvelData(
                1, mutableListOf(
                    Hero(
                        "", "", "", "", Thumbnail("", ""), ""
                    )
                )
            )
        )
        val response: Response<MarvelRoot> = Response.success(200, bodyResponse)
        val call: Call<MarvelRoot> = createCallMock(response)
        every { service.getHeroes("", "", "", 0) } answers { call }
    }

    @Test
    fun t1_when_requesting_heroes_list_then_recover() {
        interactor.getHeroes(object : MarvelCallback {
            override fun onHeroesListOK(list: MutableList<Hero>) {
                assertNotNull(list)
                assertNotEquals(0, list.size)
            }

            override fun onHeroesListKO(message: ErrorMessage?) {

            }

            override fun onHeroOK(hero: Hero) {

            }

            override fun onHeroKO(message: ErrorMessage?) {

            }
        })
    }

    @Test
    fun t2_when_creating_md5_receive_result() {
        assertNotNull(calculateMD5("23", "asdasf", "asda"))
    }
}