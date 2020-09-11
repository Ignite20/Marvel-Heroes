package com.example.marvelheroes_amaris.ui.heroes_list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelheroes_amaris.R
import com.example.marvelheroes_amaris.domain.models.Hero
import kotlinx.android.synthetic.main.hero_item.view.*

class HeroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView.rootView) {


    companion object {
        fun from(viewGroup: ViewGroup?): HeroViewHolder {
            val layoutInflater = LayoutInflater.from(viewGroup?.context)
            val view = layoutInflater.inflate(R.layout.hero_item, viewGroup, false)
            return HeroViewHolder(view)
        }
    }

    fun bind(hero: Hero): HeroViewHolder {
        itemView.tv_character_name.text = hero.name
        Glide.with(itemView.context)
            .load(hero.thumbnail.path.plus(".").plus(hero.thumbnail.extension)).centerCrop()
            .into(itemView.iv_hero)
        return this
    }
}