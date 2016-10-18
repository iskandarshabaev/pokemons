package com.ishabaev.pokemonwiki.repository


import com.ishabaev.pokew.model.Characteristics
import com.ishabaev.pokew.model.Pokemon
import com.ishabaev.pokew.model.PokemonPreview
import com.ishabaev.pokew.model.Stat
import io.realm.Realm
import rx.Observable

class LocalPokemonRepository : ILocalPokemonRepository {

    override fun pokemons(offset: Int): Observable<List<PokemonPreview>> {
        return Observable.fromCallable({ loadPokemonsLocal(offset + 1, offset) })
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

    override fun characteristic(id: Int): Observable<Characteristics> {
        return Observable.fromCallable({ loadCharacteristicLocal(id) })
    }

    private fun loadCharacteristicLocal(id: Int): Characteristics {
        var results = Realm.getDefaultInstance()
                .where(Characteristics::class.java)
                .equalTo("id", id)
                .findFirst()
        return Realm.getDefaultInstance().copyFromRealm(results)
    }

    override fun pokemon(id: Int): Observable<Pokemon> {
        return Observable.fromCallable { loadPokemon(id) }
    }

    override fun stat(id: Int): Observable<Stat> {
        return Observable.fromCallable { loadStatLocal(id) }
    }

    private fun loadStatLocal(id: Int): Stat {
        var results = Realm.getDefaultInstance()
                .where(Stat::class.java)
                .equalTo("id", id)
                .findFirst()
        return Realm.getDefaultInstance().copyFromRealm(results)
    }

    @Throws(Exception::class)
    private fun loadPokemon(id: Int): Pokemon {
        var results = Realm.getDefaultInstance()
                .where(Pokemon::class.java)
                .equalTo("id", id)
                .findFirst()
        return Realm.getDefaultInstance().copyFromRealm(results)
    }
}
