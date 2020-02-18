package com.crisgon.autocartasgui.modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

/**
 * Created by @cristhian-jg on 17/02/2020.
 */
public class Carta {

    @SerializedName("identificador")
    @Expose
    private String identificador;
    @SerializedName("marca")
    @Expose
    private String marca;
    @SerializedName("modelo")
    @Expose
    private String modelo;
    @SerializedName("foto")
    @Expose
    private byte[] foto;
    @SerializedName("motor")
    @Expose
    private Integer motor;
    @SerializedName("potencia")
    @Expose
    private Integer potencia;
    @SerializedName("velocidad")
    @Expose
    private Integer velocidad;
    @SerializedName("cilindros")
    @Expose
    private Integer cilindros;
    @SerializedName("revoluciones")
    @Expose
    private Integer revoluciones;
    @SerializedName("consumo")
    @Expose
    private Double consumo;

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public Integer getMotor() {
        return motor;
    }

    public void setMotor(Integer motor) {
        this.motor = motor;
    }

    public Integer getPotencia() {
        return potencia;
    }

    public void setPotencia(Integer potencia) {
        this.potencia = potencia;
    }

    public Integer getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(Integer velocidad) {
        this.velocidad = velocidad;
    }

    public Integer getCilindros() {
        return cilindros;
    }

    public void setCilindros(Integer cilindros) {
        this.cilindros = cilindros;
    }

    public Integer getRevoluciones() {
        return revoluciones;
    }

    public void setRevoluciones(Integer revoluciones) {
        this.revoluciones = revoluciones;
    }

    public Double getConsumo() {
        return consumo;
    }

    public void setConsumo(Double consumo) {
        this.consumo = consumo;
    }

    @Override
    public String toString() {
        return "Carta{" +
                "identificador='" + identificador + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", foto=" + Arrays.toString(foto) +
                ", motor=" + motor +
                ", potencia=" + potencia +
                ", velocidad=" + velocidad +
                ", cilindros=" + cilindros +
                ", revoluciones=" + revoluciones +
                ", consumo=" + consumo +
                '}';
    }
}
