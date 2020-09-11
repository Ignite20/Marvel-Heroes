package com.example.marvelheroes_amaris.ui.heroes_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelheroes_amaris.R
import com.example.marvelheroes_amaris.domain.models.Hero
import com.example.marvelheroes_amaris.ui.heroes_list.adapter.HeroesAdapter
import kotlinx.android.synthetic.main.heroes_list_fragment.*

class HeroesListFragment : Fragment() {

    private lateinit var viewModel: HeroesListViewModel
    private var adapter = HeroesAdapter(null, mutableListOf())
    private lateinit var layoutManager: LinearLayoutManager

    private var loadMore = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.heroes_list_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HeroesListViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        prepareRecycler()
        viewModel.getHeroes()
        observeData()
    }

    private fun prepareRecycler() {
        rvHeroes.adapter = adapter
        rvHeroes.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false).also {
            layoutManager = it
        }
        rvHeroes.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {

            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (layoutManager.findLastVisibleItemPosition() == adapter.itemCount - 1) {
                    rvHeroes.postDelayed({
                        if (loadMore) {
                            loadMore = false
                            viewModel.getHeroes(adapter.itemCount)
                        }
                    }, 50)
                }
            }
        })
    }

    private fun observeData() {
        viewModel.heroes.observe(this.viewLifecycleOwner, Observer {
            fillRecycler(it.toMutableList())
        })
    }

    private fun fillRecycler(list: MutableList<Hero>) {
        adapter.addMore(list)
        loadMore = true
    }

}