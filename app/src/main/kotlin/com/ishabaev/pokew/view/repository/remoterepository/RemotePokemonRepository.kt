package com.ishabaev.pokemonwiki.repository


import com.ishabaev.pokemonwiki.api.ApiFactory
import com.ishabaev.pokew.model.Characteristics
import com.ishabaev.pokew.model.Pokemon
import com.ishabaev.pokew.model.PokemonPreview
import com.ishabaev.pokew.model.Stat
import io.realm.Realm
import rx.Observable

class RemotePokemonRepository : IRemotePokemonRepository {

    override fun pokemons(offset: Int): Observable<List<PokemonPreview>> {
        return ApiFactory.sService.pokemons(offset)
                .flatMap({
                    listPokemonApiResult ->
                    Observable.from(listPokemonApiResult.results)
                })
                .doOnNext({ pokemonPreview ->
                    var url = pokemonPreview.url
                    if (url != null) {
                        var id = getIdFromUrl(url)
                        pokemonPreview.id = id
                    }
                })
                .toList()
                .doOnNext({
                    pokemons ->
                    Realm.getDefaultInstance()
                            .executeTransaction({
                                transaction ->
                                transaction.insertOrUpdate(pokemons)
                            })
                })
    }

    private fun getIdFromUrl(url: String): Int {
        val parts = url.split("/".toRegex())
                .dropLastWhile(String::isEmpty)
                .toTypedArray()
        val p = parts[parts.size - 1]
        return Integer.parseInt(p)
    }

    override fun characteristic(id: Int): Observable<Characteristics> {
        return ApiFactory.sService.characteristic(id)
                .doOnNext({
                    characteristic ->
                    Realm.getDefaultInstance()
                            .executeTransaction({
                                transaction ->
                                transaction.insertOrUpdate(characteristic)
                            })
                })
    }

    override fun stat(id: Int): Observable<Stat> {
        return ApiFactory.sService.stat(id)
                .doOnNext({ stat ->
                    Realm.getDefaultInstance()
                            .executeTransaction({
                                transaction ->
                                transaction.insertOrUpdate(stat)
                            })
                })
    }

    override fun pokemon(id: Int): Observable<Pokemon> {
        return ApiFactory.sService.pokemon(id)
    }
}
