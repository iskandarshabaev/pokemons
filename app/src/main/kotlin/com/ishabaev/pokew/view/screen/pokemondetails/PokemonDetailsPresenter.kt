package com.ishabaev.pokemonwiki.screen.pokemondetails

import com.ishabaev.pokemonwiki.repository.PokemonRepository
import com.ishabaev.pokew.model.Characteristics


class PokemonDetailsPresenter(private val mView: PokemonDetailsView,
                              private val mRepository: PokemonRepository) {

    fun load(id: Int) {
        /*mRepository.characteristic(id)
                .compose(RxSchedulers.async<Characteristics>())
                .subscribe({
                    this.showCharacteristics(it)
                }, { this.showException(it) })*/
    }

    private fun showCharacteristics(characteristics: Characteristics) {
        var description = characteristics.descriptions!![0].description
        mView.showDescription(description!!)
    }

    private fun showException(throwable: Throwable) {
        throwable.printStackTrace()
    }
}
