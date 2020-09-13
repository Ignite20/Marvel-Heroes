package com.example.marvelheroes_amaris.ui.heroes_list.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelheroes_amaris.domain.models.Hero

class HeroesAdapter(
    var heroesCallback: HeroesCallback? = null,
    val list: MutableList<Hero>
) : RecyclerView.Adapter<HeroViewHolder>() {

    interface HeroesCallback {
        fun onHeroClick(hero: Hero)
        fun onHeroesLoaded()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        return HeroViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        holder.bind(list[position]).apply {
            itemView.setOnClickListener {
                heroesCallback?.onHeroClick(list[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun addMore(moreList: MutableList<Hero>) {
        list.addAll(moreList)
        notifyDataSetChanged()
        heroesCallback?.onHeroesLoaded()
    }
}