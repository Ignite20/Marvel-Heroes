package com.example.marvelheroes_amaris.ui.heroes_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.marvelheroes_amaris.R
import com.example.marvelheroes_amaris.domain.models.Hero
import kotlinx.android.synthetic.main.heroe_detail_fragment.*

class HeroeDetailFragment : Fragment() {

    private lateinit var viewModel: HeroeDetailViewModel
    private val args: HeroeDetailFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.heroe_detail_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HeroeDetailViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retrieveArgs()
        observeData()
    }

    private fun observeData() {
        viewModel.heroes.observe(this.viewLifecycleOwner, Observer {
            drawHero(it)
        })
    }

    private fun drawHero(hero: Hero) {
        tv_character_name.text = hero.name
        Glide.with(iv_hero.context)
            .load(hero.thumbnail.path.plus(".").plus(hero.thumbnail.extension))
            .into(iv_hero)

    }

    private fun retrieveArgs() {
        args.let {
            viewModel.getHeroDetails(it.heroId)
        }
    }
}