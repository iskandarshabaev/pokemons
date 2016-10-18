package com.ishabaev.pokemonwiki.repository

object RepositoryProvider {

    var sPokemonRepository: PokemonRepository

    init {
        sPokemonRepository = DefaultPokemonRepository(
                LocalPokemonRepository(), RemotePokemonRepository())
    }

    fun setPokemonRepository(pokemonRepository: PokemonRepository) {
        sPokemonRepository = pokemonRepository
    }

}
