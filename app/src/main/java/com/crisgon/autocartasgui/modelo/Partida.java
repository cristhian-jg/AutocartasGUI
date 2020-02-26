package com.crisgon.autocartasgui.modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Date;

/**
 *  Clase modelo que representa la tabla Partida
 *
 * Created by @cristhian-jg on 17/02/2020.
 */
public class Partida {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("jugador")
    @Expose
    private String jugador;
    @SerializedName("ganada")
    @Expose
    private Boolean ganada;
    @SerializedName("terminada")
    @Expose
    private Boolean terminada;
    @SerializedName("fecha")
    @Expose
    private Date fecha;

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

    public Boolean getGanada() {
        return ganada;
    }

    public void setGanada(Boolean ganada) {
        this.ganada = ganada;
    }

    public Boolean getTerminada() {
        return terminada;
    }

    public void setTerminada(Boolean terminada) {
        this.terminada = terminada;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Partida{" +
                "id=" + id +
                ", jugador='" + jugador + '\'' +
                ", ganada=" + ganada +
                ", terminada=" + terminada +
                ", fecha=" + fecha +
                '}';
    }
}
