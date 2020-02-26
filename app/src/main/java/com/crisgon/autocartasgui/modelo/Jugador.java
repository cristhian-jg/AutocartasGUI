package com.crisgon.autocartasgui.modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Clase modelo jugador que representa la tabla Jugador.
 *
 * Created by @cristhian-jg on 17/02/2020.
 */
public class Jugador implements Serializable {

    @SerializedName("nickname")
    @Expose
    private String nickname;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("password")
    @Expose
    private String password;

    public Jugador(String nickname, String nombre, String password) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "nickname='" + nickname + '\'' +
                ", nombre='" + nombre + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
