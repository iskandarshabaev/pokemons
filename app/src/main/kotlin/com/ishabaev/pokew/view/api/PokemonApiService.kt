package com.ishabaev.pokemonwiki.api

import com.ishabaev.pokew.model.Characteristics
import com.ishabaev.pokew.model.Pokemon
import com.ishabaev.pokew.model.PokemonApiResult
import com.ishabaev.pokew.model.PokemonPreview
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable


interface PokemonApiService {

    @GET("pokemon/{id}")
    fun pokemon(@Path("id") id: Int): Observable<Pokemon>

    @GET("characteristic/{id}")
    fun characteristic(@Path("id") id: Int): Observable<Characteristics>


    @GET("pokemon/")
    fun pokemons(@Query("offset") offset: Int): Observable<PokemonApiResult<List<PokemonPreview>>>

}