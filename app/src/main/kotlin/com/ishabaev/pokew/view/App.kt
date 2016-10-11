package com.ishabaev.pokemonwiki


import android.app.Application

import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.rx.RealmObservableFactory

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        val picasso = Picasso.Builder(this).downloader(OkHttp3Downloader(this)).build()
        Picasso.setSingletonInstance(picasso)

        val configuration = RealmConfiguration.Builder(this)
                .rxFactory(RealmObservableFactory())
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(configuration)
        Realm.getDefaultInstance()
    }
}
