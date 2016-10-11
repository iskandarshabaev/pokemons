package com.ishabaev.pokew.model;


import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

@RealmClass
public class Pokemon extends RealmObject {

    @PrimaryKey
    @SerializedName("id")
    private Integer id;


    @SerializedName("name")
    private String name;

    @SerializedName("effect_entries")
    private RealmList<EffectEntry> effectEntries;
}
