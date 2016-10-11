package com.ishabaev.pokemonwiki.util

import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


object RxSchedulers {

    fun <T> async(): Observable.Transformer<T, T> = Observable.Transformer<T, T> {
        observable ->
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}