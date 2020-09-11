package com.example.marvelheroes_amaris.ui.heroes_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelheroes_amaris.R
import com.example.marvelheroes_amaris.domain.models.Hero
import com.example.marvelheroes_amaris.ui.heroes_list.adapter.HeroesAdapter
import kotlinx.android.synthetic.main.heroes_list_fragment.*

class HeroesListFragment : Fragment(), HeroesAdapter.OnItemClick {

    private lateinit var viewModel: HeroesListViewModel
    private lateinit var adapter: HeroesAdapter
    private lateinit var layoutManager: LinearLayoutManager

    private var loadMore = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.heroes_list_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HeroesListViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        prepareRecycler()
        if (viewModel.mList.size > 0) {
            adapter.addMore(viewModel.mList, false)
        } else {
            viewModel.getHeroes()
        }
        observeData()
    }

    private fun prepareRecycler() {
        rvHeroes.adapter = HeroesAdapter(this, mutableListOf()).also {
            adapter = it
        }
        rvHeroes.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false).also {
            layoutManager = it
        }
        rvHeroes.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_SETTLING) {
                    if (layoutManager.findLastVisibleItemPosition() == adapter.itemCount - 1) {
                        rvHeroes.postDelayed({
                            if (loadMore) {
                                viewModel.getHeroes(adapter.itemCount)
                                loadMore = false
                            }
                        }, 50)
                    }
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

            }
        })
    }

    private fun observeData() {
        viewModel.heroes.observe(this.viewLifecycleOwner, {
            fillRecycler(it.toMutableList())
        })
    }

    private fun fillRecycler(list: MutableList<Hero>) {
        adapter.addMore(list, true)
    }

    override fun onPause() {
        super.onPause()
        viewModel.heroes.removeObservers(this.viewLifecycleOwner)
    }

    override fun onListLoaded() {
        loadMore = true
    }

    override fun onHeroClick(hero: Hero) {
        Toast.makeText(requireContext(), hero.name, Toast.LENGTH_SHORT).show()
        findNavController().navigate(
            HeroesListFragmentDirections.actionHeroesListFragmentToHeroeDetailFragment(
                hero.id
            )
        )
    }

}