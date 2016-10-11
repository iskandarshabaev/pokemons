package com.ishabaev.pokemonwiki.screen.pokemonlist

import com.ishabaev.pokew.model.PokemonPreview

interface PokemonListView {

    fun showException()

    fun showProgress()

    fun hideProgress()

    fun addPokemons(pokemonPreviews: List<PokemonPreview>)

    fun setPokemons(pokemonPreviews: List<PokemonPreview>)
}
