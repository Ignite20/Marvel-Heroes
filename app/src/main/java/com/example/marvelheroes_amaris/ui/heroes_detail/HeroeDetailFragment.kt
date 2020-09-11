package com.example.marvelheroes_amaris.ui.heroes_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.marvelheroes_amaris.R

class HeroeDetailFragment : Fragment() {

    companion object {
        fun newInstance() = HeroeDetailFragment()
    }

    private lateinit var viewModel: HeroeDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.heroe_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HeroeDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}