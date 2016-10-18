package com.ishabaev.pokemonwiki.repository


import com.ishabaev.pokew.model.Characteristics
import com.ishabaev.pokew.model.Pokemon
import com.ishabaev.pokew.model.PokemonPreview
import com.ishabaev.pokew.model.Stat
import rx.Observable

interface IRemotePokemonRepository {

    fun pokemon(id: Int): Observable<Pokemon>

    fun pokemons(offset: Int): Observable<List<PokemonPreview>>

    fun characteristic(id: Int): Observable<Characteristics>

    fun stat(id: Int): Observable<Stat>
}
