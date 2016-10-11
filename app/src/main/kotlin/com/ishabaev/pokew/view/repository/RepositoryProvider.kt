package com.ishabaev.pokemonwiki.repository

object RepositoryProvider {

    var sPokemonRepository: PokemonRepository

    init {
        sPokemonRepository = DefaultPokemonRepository()
    }

    fun setPokemonRepository(pokemonRepository: PokemonRepository) {
        sPokemonRepository = pokemonRepository
    }

}
