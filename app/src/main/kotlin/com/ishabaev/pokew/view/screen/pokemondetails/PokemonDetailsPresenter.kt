package com.ishabaev.pokemonwiki.screen.pokemondetails

import com.ishabaev.pokemonwiki.repository.PokemonRepository
import com.ishabaev.pokemonwiki.util.RxSchedulers
import com.ishabaev.pokew.model.Pokemon


class PokemonDetailsPresenter(private val mView: PokemonDetailsView,
                              private val mRepository: PokemonRepository) {

    fun load(id: Int) {
        mRepository.pokemon(id)
                .compose(RxSchedulers.async<Pokemon>())
                .subscribe({ this.showCharacteristics(it) }, { this.showException(it) })
    }

    private fun showCharacteristics(pokemon: Pokemon) {
        mView.baseExp(pokemon.baseExperience.toString())
        mView.showHeight(pokemon.weight.toString())
        mView.showWeight(pokemon.height.toString())
        mView.showStat(pokemon.stats)
    }

    private fun showException(throwable: Throwable) {
        throwable.printStackTrace()
    }
}
