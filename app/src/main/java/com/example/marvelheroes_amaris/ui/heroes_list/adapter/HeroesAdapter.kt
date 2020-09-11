package com.example.marvelheroes_amaris.ui.heroes_list.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelheroes_amaris.domain.models.Hero

class HeroesAdapter(
    var onItemClick: OnItemClick? = null,
    val list: MutableList<Hero>
) : RecyclerView.Adapter<HeroViewHolder>() {

    interface OnItemClick {
        fun onHeroClick(hero: Hero)
        fun onListLoaded()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        return HeroViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        holder.bind(list[position]).apply {
            itemView.setOnClickListener {
                onItemClick?.onHeroClick(list[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun addMore(moreList: MutableList<Hero>, notify: Boolean) {
        list.addAll(moreList)
        notifyDataSetChanged()
        if (notify) onItemClick?.onListLoaded()
    }
}