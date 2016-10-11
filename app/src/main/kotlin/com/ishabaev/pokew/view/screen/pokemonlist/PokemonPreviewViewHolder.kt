package com.ishabaev.pokemonwiki.screen.pokemonlist

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.ishabaev.pokemonwiki.util.ImageHelper
import com.ishabaev.pokew.R
import com.ishabaev.pokew.model.PokemonPreview

class PokemonPreviewViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val spriteImageView: ImageView
    private val mNameTextView: TextView

    init {
        spriteImageView = view.findViewById(R.id.sprite) as ImageView
        mNameTextView = view.findViewById(R.id.name) as TextView
    }

    fun bind(pokemonPreview: PokemonPreview) {
        ImageHelper.loadSprite(spriteImageView, pokemonPreview.getId()!!)
        mNameTextView.text = pokemonPreview.getName()
    }
}
