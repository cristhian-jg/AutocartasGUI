package com.crisgon.autocartasgui.modelo.juego;

import com.crisgon.autocartasgui.modelo.Carta;

import java.util.ArrayList;

/**
 * Created by @cristhian-jg on 20/02/2020.
 */
public class Juego {

    private int idGame;
    private ArrayList<Carta> cartasJugador;
    private ArrayList<Carta> cartasMaquina;
    private String primerTurno;

    public Juego() {
    }

    public Juego(int idGame, ArrayList<Carta> cartasJugador, ArrayList<Carta> cartasMaquina, String idSession) {
        this.idGame = idGame;
        this.cartasJugador = cartasJugador;
        this.cartasMaquina = cartasMaquina;
        this.primerTurno = idSession;
    }

    public Juego(int idGame, ArrayList<Carta> cartasJugador, ArrayList<Carta> cartasMaquina) {
        super();
        this.idGame = idGame;
        this.cartasJugador = cartasJugador;
        this.cartasMaquina = cartasMaquina;
        this.primerTurno = "CPU";
    }

    public int getIdGame() {
        return idGame;
    }

    public void setIdGame(int idGame) {
        this.idGame = idGame;
    }

    public ArrayList<Carta> getCartasJugador() {
        return cartasJugador;
    }
    public void setCartasJugador(ArrayList<Carta> cartasJugador) {
        this.cartasJugador = cartasJugador;
    }
    public ArrayList<Carta> getCartasMaquina() {
        return cartasMaquina;
    }
    public void setCartasMaquina(ArrayList<Carta> cartasMaquina) {
        this.cartasMaquina = cartasMaquina;
    }
    public String getPrimerTurno() {
        return primerTurno;
    }
    public void setPrimerTurno(String primerTurno) {
        this.primerTurno = primerTurno;
    }

}
