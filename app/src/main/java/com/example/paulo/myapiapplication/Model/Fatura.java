package com.example.paulo.myapiapplication.Model;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@JsonRootName("fatura")
public class Fatura implements Serializable {

    @SerializedName("codigo")
    public int codigo;

    @SerializedName("descricao")
    public String descricao;

    @SerializedName("status")
    public int status;

    @SerializedName("valor")
    public double valor;
}
