package com.crisgon.autocartasgui.modelo.juego;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Clase que utilizo para empaquetar la información de la jugada que hace el
 * jugador o la CPU.
 *
 * Created by @cristhian-jg on 25/02/2020.
 */
public class Jugada {

    @Expose
    private String idSession;
    @Expose
    private int idGame;
    @Expose
    private String idCard;
    @Expose
    private String feature;
    @Expose
    private int hand;

    public Jugada(String idSession, int idGame, String idCard, String feature, int hand) {
        this.idSession = idSession;
        this.idGame = idGame;
        this.idCard = idCard;
        this.feature = feature;
        this.hand = hand;
    }

    public String getIdSession() {
        return idSession;
    }

    public int getIdGame() {
        return idGame;
    }

    public String getIdCard() {
        return idCard;
    }

    public String getFeature() {
        return feature;
    }

    public int getHand() {
        return hand;
    }

    public void setIdSession(String idSession) {
        this.idSession = idSession;
    }

    public void setIdGame(int idGame) {
        this.idGame = idGame;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public void setHand(int hand) {
        this.hand = hand;
    }

    @Override
    public String toString() {
        return "Jugada{" +
                "idSession='" + idSession + '\'' +
                ", idGame=" + idGame +
                ", idCard='" + idCard + '\'' +
                ", feature='" + feature + '\'' +
                ", hand=" + hand +
                '}';
    }
}
