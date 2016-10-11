package com.ishabaev.pokemonwiki.repository


import com.ishabaev.pokew.model.PokemonPreview
import rx.Observable

interface PokemonRepository {

    fun pokemons(offset: Int): Observable<List<PokemonPreview>>

    fun restorePokemons(position: Int): Observable<List<PokemonPreview>>

    //fun characteristic(id: Int): Observable<Characteristics>
}
