package com.ishabaev.pokemonwiki.repository


import com.ishabaev.pokemonwiki.api.ApiFactory
import com.ishabaev.pokew.model.Characteristics
import com.ishabaev.pokew.model.PokemonPreview
import io.realm.Realm
import rx.Observable

class DefaultPokemonRepository : PokemonRepository {

    override fun pokemons(offset: Int): Observable<List<PokemonPreview>> {
        return Observable.fromCallable({ loadPokemonsLocal(offset + 1, offset) })
                //.onErrorResumeNext({ loadPokemonsRemote(offset) })
                .flatMap({ pokemons ->
                    if (pokemons.size < 20) {
                        loadPokemonsRemote(offset)
                    } else {
                        Observable.just(pokemons)
                    }
                })
    }

    override fun restorePokemons(position: Int): Observable<List<PokemonPreview>> {
        return Observable.fromCallable({ loadPokemonsLocal(position) })
    }

    private fun loadPokemonsLocal(start: Int, offset: Int): List<PokemonPreview> {
        var results = Realm.getDefaultInstance()
                .where(PokemonPreview::class.java)
                .between("id", start, offset + 20)
                .findAll()
        return Realm.getDefaultInstance().copyFromRealm(results)
    }

    private fun loadPokemonsLocal(position: Int): List<PokemonPreview> {
        var results = Realm.getDefaultInstance()
                .where(PokemonPreview::class.java)
                .between("id", 0, position)
                .findAll()
        return Realm.getDefaultInstance().copyFromRealm(results)
    }

    private fun loadPokemonsRemote(offset: Int): Observable<List<PokemonPreview>> {
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
        return Observable.fromCallable({ loadCharacteristicLocal(id) })
                .flatMap({ characteristic ->
                    if (characteristic == null) {
                        loadCharacteristicRemote(id)
                    } else {
                        Observable.just(characteristic)
                    }
                })
                .onErrorResumeNext({ loadCharacteristicRemote(id) })
    }

    private fun loadCharacteristicLocal(id: Int): Characteristics {
        var results = Realm.getDefaultInstance()
                .where(Characteristics::class.java)
                .equalTo("id", id)
                .findFirst()
        return Realm.getDefaultInstance().copyFromRealm(results)
    }

    private fun loadCharacteristicRemote(offset: Int): Observable<Characteristics> {
        return ApiFactory.sService.characteristic(offset)
                .doOnNext({
                    characteristic ->
                    Realm.getDefaultInstance()
                            .executeTransaction({
                                transaction ->
                                transaction.insertOrUpdate(characteristic)
                            })
                })
    }
}
