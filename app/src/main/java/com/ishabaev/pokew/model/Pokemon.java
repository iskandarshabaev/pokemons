package com.ishabaev.pokew.model;


import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

@RealmClass
public class Pokemon extends RealmObject {

    @PrimaryKey
    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("base_experience")
    private Integer baseExperience;

    @SerializedName("height")
    private Integer height;

    @SerializedName("weight")
    private Integer weight;

    @SerializedName("species")
    private PokemonSpecies species;

    @SerializedName("stats")
    private RealmList<PokemonStat> stats;

    @SerializedName("effect_entries")
    private RealmList<EffectEntry> effectEntries;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getBaseExperience() {
        return baseExperience;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWeight() {
        return weight;
    }

    public PokemonSpecies getSpecies() {
        return species;
    }

    public RealmList<PokemonStat> getStats() {
        return stats;
    }

    public RealmList<EffectEntry> getEffectEntries() {
        return effectEntries;
    }


}
