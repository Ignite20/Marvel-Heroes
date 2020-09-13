package com.example.marvelheroes_amaris.ui.heroes_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.marvelheroes_amaris.domain.api.MarvelInteractor
import com.example.marvelheroes_amaris.domain.api.RetrofitClient
import com.example.marvelheroes_amaris.domain.models.Hero
import com.example.marvelheroes_amaris.domain.repository.HeroesRepository
import com.example.marvelheroes_amaris.domain.repository.HeroesRepositoryImpl
import com.example.marvelheroes_amaris.ui.base.BaseViewModel
import com.example.marvelheroes_amaris.ui.heroes_list.usecase.HeroesUseCase
import com.example.marvelheroes_amaris.ui.heroes_list.usecase.HeroesUseCaseImplementation
import kotlinx.coroutines.launch

class HeroeDetailViewModel : BaseViewModel() {
    val uiData = MediatorLiveData<Hero>()
    val heroes: LiveData<Hero> = uiData

    private val retrofitClient = RetrofitClient()
    private val marvelInteractor = MarvelInteractor(retrofitClient)
    private val repo: HeroesRepository = HeroesRepositoryImpl(marvelInteractor)
    private val heroesUseCase: HeroesUseCase = HeroesUseCaseImplementation(repo)

    init {
        setupObservers()
    }

    private fun setupObservers() {
        uiData.apply {
            addSource(heroesUseCase.heroResponse.data) { response ->
                createAndPostHeroes(response)
            }
        }
    }

    fun createAndPostHeroes(response: Hero) {
        viewModelScope.launch {
            uiData.postValue(response)
        }
    }

    fun getHeroDetails(heroId: String) {
        heroesUseCase.getHero(heroId)
    }
}