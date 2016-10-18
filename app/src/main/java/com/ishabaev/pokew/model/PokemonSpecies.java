package com.ishabaev.pokew.model;


import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class PokemonSpecies extends RealmObject {

    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

}
