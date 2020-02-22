package com.crisgon.autocartasgui.modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by @cristhian-jg on 17/02/2020.
 */
public class Estadistica  {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("jugador")
    @Expose
    private String jugador;
    @SerializedName("ganadas")
    @Expose
    private Integer ganadas;
    @SerializedName("perdidas")
    @Expose
    private Integer perdidas;
    @SerializedName("empatadas")
    @Expose
    private Integer empatadas;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJugador() {
        return jugador;
    }

    public void setJugador(String jugador) {
        this.jugador = jugador;
    }

    public Integer getGanadas() {
        return ganadas;
    }

    public void setGanadas(Integer ganadas) {
        this.ganadas = ganadas;
    }

    public Integer getPerdidas() {
        return perdidas;
    }

    public void setPerdidas(Integer perdidas) {
        this.perdidas = perdidas;
    }

    public Integer getEmpatadas() {
        return empatadas;
    }

    public void setEmpatadas(Integer empatadas) {
        this.empatadas = empatadas;
    }
}
