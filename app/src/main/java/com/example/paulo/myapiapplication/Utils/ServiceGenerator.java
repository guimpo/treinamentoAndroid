package com.example.paulo.myapiapplication.Utils;

import com.example.paulo.myapiapplication.Model.Usuario;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.lang.reflect.Type;

public abstract class ServiceGenerator {
    public static String STRING_TOKEN;
    public static Usuario USER;

    public static Retrofit retrofit(){
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("https://api-senai-tcs.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static Retrofit retrofit(Type type){
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("https://api-senai-tcs.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create(JsonUtils.getGson(type)))
                .build();

        return retrofit;
    }
}
