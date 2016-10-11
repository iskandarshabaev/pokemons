package com.ishabaev.pokemonwiki.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ishabaev.pokew.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {

    private val GSON_DATE: Gson = GsonBuilder().setDateFormat("yyyy-MM-dd").create();

    private var sClient: OkHttpClient

    init {
        sClient = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BASIC))
                .build()
    }

    var sService: PokemonApiService

    init {
        val retrofit = Retrofit.Builder().baseUrl(BuildConfig.API_ENDPOINT)
                .client(sClient)
                .addConverterFactory(GsonConverterFactory.create(GSON_DATE))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()

        sService = retrofit.create(PokemonApiService::class.java)
    }
}


