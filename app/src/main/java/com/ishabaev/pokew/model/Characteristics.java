package com.ishabaev.pokew.model;


import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Characteristics extends RealmObject{

    @PrimaryKey
    @SerializedName("id")
    private Integer id;

    @SerializedName("descriptions")
    private RealmList<Description> descriptions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Description> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(RealmList<Description> descriptions) {
        this.descriptions = descriptions;
    }
}
