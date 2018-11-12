package com.example.paulo.myapiapplication.Model;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@JsonRootName("user")
public class Usuario implements Serializable {

    @SerializedName("name")
    public String name;

    @SerializedName("email")
    public String email;

    @SerializedName("password")
    public String password;

    @SerializedName("picture")
    public String picture;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
