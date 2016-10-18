package com.ishabaev.pokew.model;


import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Language extends RealmObject {

    @SerializedName("name")
    private String name;

    @SerializedName("url")
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
