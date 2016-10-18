package com.ishabaev.pokew.model;


import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Stat extends RealmObject {

    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("characteristics")
    private RealmList<Characteristics> characteristics;

    public String getName() {
        return name;
    }
}
