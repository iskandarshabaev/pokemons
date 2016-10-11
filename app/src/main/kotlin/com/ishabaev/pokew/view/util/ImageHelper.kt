package com.ishabaev.pokemonwiki.util

import android.support.annotation.NonNull
import android.widget.ImageView
import com.ishabaev.pokemonwiki.CircleTransform
import com.ishabaev.pokew.R
import com.squareup.picasso.Picasso


object ImageHelper {

    fun loadImage(imageView: ImageView, @NonNull url: String) = Picasso.with(imageView.context)
            .load(url).noFade()
            //.placeholder(R.drawable.game_placeholder)
            .into(imageView)

    fun loadSprite(imageView: ImageView, id: Int) = Picasso.with(imageView.context)
            //.load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/$id.png")
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other-sprites/official-artwork/$id.png")
            .placeholder(R.drawable.pokeball)
            .error(R.drawable.pokeball)
            .transform(CircleTransform())
            .into(imageView)
}