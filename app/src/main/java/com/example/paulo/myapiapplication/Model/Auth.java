package com.example.paulo.myapiapplication.Model;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.gson.annotations.SerializedName;

@JsonRootName("auth")
public class Auth {

    @SerializedName("email")
    public String email;

    @SerializedName("password")
    public String password;
}
