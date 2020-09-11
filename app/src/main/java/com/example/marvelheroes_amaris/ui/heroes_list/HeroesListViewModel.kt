package com.example.marvelheroes_amaris.ui.heroes_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.marvelheroes_amaris.domain.models.Hero
import com.example.marvelheroes_amaris.domain.repository.HeroesRepository
import com.example.marvelheroes_amaris.domain.repository.HeroesRepositoryImpl
import com.example.marvelheroes_amaris.ui.base.BaseViewModel
import com.example.marvelheroes_amaris.ui.heroes_list.usecase.HeroesUseCase
import com.example.marvelheroes_amaris.ui.heroes_list.usecase.HeroesUseCaseImplementation
import kotlinx.coroutines.launch

class HeroesListViewModel : BaseViewModel() {

    val uiData = MediatorLiveData<List<Hero>>()
    val heroes: LiveData<List<Hero>> = uiData

    var mList: MutableList<Hero> = mutableListOf()

    private val repo: HeroesRepository = HeroesRepositoryImpl()
    private val heroesUseCase: HeroesUseCase = HeroesUseCaseImplementation(repo)

    init {
        setupObservers()
    }

    fun getHeroes() {
        heroesUseCase.getHeroes()
    }

    fun getHeroes(offset: Int) {
        heroesUseCase.getHeroes(offset)
    }

    private fun setupObservers() {
        uiData.apply {
            addSource(heroesUseCase.heroesResponse.data) { response ->
                createAndPostHeroes(response)
            }
        }
    }

    fun createAndPostHeroes(response: List<Hero>) {
        viewModelScope.launch {
            uiData.postValue(response)
            mList.addAll(response)
        }
    }
}