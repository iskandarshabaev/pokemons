package com.ishabaev.pokew.model;


import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class NamedAPIResource {

    @SerializedName("name")
    private String name;

    @SerializedName("url")
    private String url;

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public int getId() {
        return getIdFromUrl(url);
    }

    public int getIdFromUrl(@NonNull String url) {
        String[] parts = url.split("/");
        String id = parts[parts.length - 1];
        return Integer.parseInt(id);
    }
}
