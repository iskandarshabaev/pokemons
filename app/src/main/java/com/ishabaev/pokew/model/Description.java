package com.ishabaev.pokew.model;


import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

@RealmClass
public class Description extends RealmObject {

    @SerializedName("description")
    private String description;

    @SerializedName("language")
    private Language language;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
