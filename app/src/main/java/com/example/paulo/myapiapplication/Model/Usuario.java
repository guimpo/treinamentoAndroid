package com.example.paulo.myapiapplication.Model;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

@JsonRootName("user")
public class Usuario implements Serializable {

    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("email")
    public String email;

    @SerializedName("password")
    public String password;

    @SerializedName("telefone")
    public String telefone;

    @SerializedName("cpf")
    public String cpf;

    @SerializedName("saldo")
    public double saldo;

    @SerializedName("descricao")
    public String descricao;

    @SerializedName("created_at")
    public String created_at;

    @SerializedName("last_login")
    public String last_login;

    @SerializedName("picture")
    public String picture;

    @SerializedName("faturas")
    public List<Fatura> faturas;
}
