package com.example.paulo.myapiapplication.Model;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Fatura implements Serializable {

    @SerializedName("codigo")
    private String codigo;

    @SerializedName("descricao")
    private String descricao;

    @SerializedName("status")
    private int status;

    @SerializedName("valor")
    private double valor;

    public Fatura(){}

    public Fatura(String codigo, double valor, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
