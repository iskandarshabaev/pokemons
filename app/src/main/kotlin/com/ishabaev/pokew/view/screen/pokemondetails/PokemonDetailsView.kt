package com.ishabaev.pokemonwiki.screen.pokemondetails

import com.ishabaev.pokew.model.PokemonStat


interface PokemonDetailsView {

    fun showWeight(weight: String)

    fun showHeight(height: String)

    fun baseExp(exp: String)

    fun showStat(stats: List<PokemonStat>)
}