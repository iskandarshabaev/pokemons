package com.ishabaev.pokemonwiki.screen.pokemonlist


import com.ishabaev.pokemonwiki.repository.PokemonRepository
import com.ishabaev.pokemonwiki.util.RxSchedulers
import com.ishabaev.pokew.model.PokemonPreview

class PokemonListPresenter(private val mView: PokemonListView, private val mRepository: PokemonRepository) {
    private var mOffset: Int = 0

    fun nextPokemonList() {
        mRepository.pokemons(mOffset)
                .compose(RxSchedulers.async<List<PokemonPreview>>())
                .subscribe({
                    this.addPokemons(it)
                    mOffset += it.size
                }, { this.showException(it) })
    }

    fun restore(position: Int) {
        mRepository.restorePokemons(position)
                .compose(RxSchedulers.async<List<PokemonPreview>>())
                .subscribe({
                    this.setPokemons(it)
                    mOffset += it.size
                }, { this.showException(it) })
    }

    private fun addPokemons(pokemonPreviews: List<PokemonPreview>) {
        mView.addPokemons(pokemonPreviews)
    }

    private fun setPokemons(pokemonPreviews: List<PokemonPreview>) {
        mView.setPokemons(pokemonPreviews)
    }


    private fun showException(throwable: Throwable) {
        throwable.printStackTrace()
    }
}
