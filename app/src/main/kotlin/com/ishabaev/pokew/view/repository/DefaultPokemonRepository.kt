package com.ishabaev.pokemonwiki.repository


import com.ishabaev.pokew.model.*
import io.realm.Realm
import rx.Observable

class DefaultPokemonRepository(
        private var mLocalRepository: ILocalPokemonRepository,
        private var mRemoteRepository: IRemotePokemonRepository
) : PokemonRepository {

    override fun pokemons(offset: Int): Observable<List<PokemonPreview>> {
        return mLocalRepository.pokemons(offset)
                .flatMap({ pokemons ->
                    if (pokemons.size < 20) {
                        mRemoteRepository.pokemons(offset)
                    } else {
                        Observable.just(pokemons)
                    }
                })
    }

    override fun restorePokemons(position: Int): Observable<List<PokemonPreview>> {
        return mLocalRepository.restorePokemons(position)
    }

    override fun characteristic(id: Int): Observable<Characteristics> {
        return mLocalRepository.characteristic(id)
                .onErrorResumeNext({ mRemoteRepository.characteristic(id) })
    }

    override fun pokemon(id: Int): Observable<Pokemon> {
        return mLocalRepository.pokemon(id)
                .onErrorResumeNext({
                    mRemoteRepository.pokemon(id)
                            .flatMap({ pokemon ->
                                Observable.zip(Observable.just(pokemon),
                                        loadStats(pokemon.stats),
                                        ({ dd, ss -> dd })
                                )
                            })
                            .doOnNext({
                                Realm.getDefaultInstance()
                                        .executeTransaction({
                                            transaction ->
                                            transaction.insertOrUpdate(it)
                                        })
                            })
                })
    }

    private fun loadStats(stats: List<PokemonStat>): Observable<List<PokemonStat>> {
        return Observable.from(stats)
                .flatMap({ getStatId(it) })
                .toList()
    }

    private fun getStatId(stat: PokemonStat): Observable<PokemonStat> {
        return Observable
                .zip(Observable.just(stat), stat(stat.stat.id),
                        ({ pokemonStat, stat ->
                            pokemonStat.statVal = stat
                            pokemonStat
                        }))
    }

    override fun stat(id: Int): Observable<Stat> {
        return mLocalRepository.stat(id)
                .onErrorResumeNext({
                    mRemoteRepository.stat(id).
                            doOnNext({
                                stat ->
                                Realm.getDefaultInstance()
                                        .executeTransaction({
                                            transaction ->
                                            transaction.insertOrUpdate(stat)
                                        })
                            })
                })

    }
}
