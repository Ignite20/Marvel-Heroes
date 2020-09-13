package com.example.marvelheroes_amaris.ui.heroes_list

import androidx.lifecycle.LiveData
import com.example.marvelheroes_amaris.common.ErrorMessage
import com.example.marvelheroes_amaris.common.StateLiveData
import com.example.marvelheroes_amaris.domain.api.MarvelInteractor
import com.example.marvelheroes_amaris.domain.api.RetrofitClient
import com.example.marvelheroes_amaris.domain.models.Hero
import com.example.marvelheroes_amaris.domain.repository.HeroesRepository
import com.example.marvelheroes_amaris.domain.repository.HeroesRepositoryImpl
import com.example.marvelheroes_amaris.ui.base.BaseViewModel
import com.example.marvelheroes_amaris.ui.heroes_list.usecase.HeroesUseCase
import com.example.marvelheroes_amaris.ui.heroes_list.usecase.HeroesUseCaseImplementation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HeroesListViewModel : BaseViewModel() {

    val uiData = StateLiveData<List<Hero>>()
    val heroes: LiveData<List<Hero>> = uiData

    var mList: MutableList<Hero> = mutableListOf()

    // The lines below can be opted out in a Module if Dagger is implemented
    private val retrofitClient = RetrofitClient()
    private val marvelInteractor = MarvelInteractor(retrofitClient)
    private val repo: HeroesRepository = HeroesRepositoryImpl(marvelInteractor)
    private val heroesUseCase: HeroesUseCase = HeroesUseCaseImplementation(repo)

    init {
        setupObservers()
    }

    fun getHeroes(offset: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            heroesUseCase.getHeroes(offset)
        }

    }

    private fun setupObservers() {
        uiData.apply {
            addSource(heroesUseCase.heroesResponse.data) { response ->
                createAndPostHeroes(response)
            }
            addSource(heroesUseCase.errorResponse.data) {
                uiData.postError(mutableListOf())
            }
        }
    }

    fun createAndPostHeroes(response: List<Hero>) {
        viewModelScope.launch {
            uiData.postValue(response)
            mList.addAll(response)
        }
    }

    fun postError(response: ErrorMessage) {
        viewModelScope.launch(Dispatchers.Main) {
            uiData.postError(mutableListOf())
        }
    }
}