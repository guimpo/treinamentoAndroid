package com.example.paulo.myapiapplication.API;

import com.example.paulo.myapiapplication.Model.Auth;
import com.example.paulo.myapiapplication.Model.Fatura;
import com.example.paulo.myapiapplication.Model.Usuario;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.HashMap;
import java.util.List;

public interface API {

    @POST("/users/create")
    Call<HashMap<String, String>> registerUser(@Body Usuario user);

    @GET("/users")
    Call<List<Usuario>> getUsers(@Header("Authorization") String authorization);

    @POST("/user_token")
    Call<HashMap<String, String>> authUser(@Body Auth auth);

    @GET("/users/current")
    Call<Usuario> getUser(@Header("Authorization") String token);

    @POST("/faturas/create")
    Call<HashMap<String, String>> registerFatura(@Header("Authorization") String token, @Body Fatura fatura);

    @GET("/faturas/pay/{id}")
    public Call<HashMap<String,String>> pagarFatura(@Header("Authorization") String token, @Path("id") String id);

    @PATCH("user/update")
    Call<HashMap<String, String>> editar(@Header("Authorization") String token, @Body Usuario user);
}
