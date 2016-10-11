package com.ishabaev.pokew.model;


import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class PokemonPreview extends RealmObject {

    @SerializedName("url")
    private String url;

    @SerializedName("name")
    private String name;

    @PrimaryKey
    @SerializedName("id")
    private Integer id;

}
