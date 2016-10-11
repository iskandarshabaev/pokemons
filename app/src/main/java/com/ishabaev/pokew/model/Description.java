package com.ishabaev.pokew.model;


import com.google.gson.annotations.SerializedName;

public class Description {

    @SerializedName("description")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
