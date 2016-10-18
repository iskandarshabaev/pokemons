package com.ishabaev.pokew.model;


import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;

public class PokemonStat extends RealmObject {

    @Ignore
    @SerializedName("stat")
    private NamedAPIResource stat;

    @SerializedName("stat_val")
    private Stat statVal;

    @SerializedName("effort")
    private Integer effort;

    @SerializedName("base_stat")
    private Integer baseStat;

    public Stat getStatVal() {
        return statVal;
    }

    public Integer getEffort() {
        return effort;
    }

    public Integer getBaseStat() {
        return baseStat;
    }

    public NamedAPIResource getStat() {
        return stat;
    }

    public void setStatVal(Stat statVal) {
        this.statVal = statVal;
    }
}
