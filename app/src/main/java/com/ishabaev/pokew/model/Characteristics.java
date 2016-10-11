package com.ishabaev.pokew.model;


import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.annotations.PrimaryKey;

public class Characteristics {

    @PrimaryKey
    @SerializedName("id")
    private Integer id;

    @SerializedName("descriptions")
    private List<Description> descriptions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Description> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<Description> descriptions) {
        this.descriptions = descriptions;
    }
}
