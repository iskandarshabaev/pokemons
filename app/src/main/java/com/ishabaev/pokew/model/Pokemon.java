package com.ishabaev.pokew.model;


import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.annotations.PrimaryKey;

public class Pokemon {

    @PrimaryKey
    @SerializedName("id")
    private Integer id;


    @SerializedName("name")
    private String name;

    @SerializedName("effect_entries")
    private List<EffectEntry> effectEntries;
}
