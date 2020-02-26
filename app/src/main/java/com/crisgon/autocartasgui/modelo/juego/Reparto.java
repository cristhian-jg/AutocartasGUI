package com.crisgon.autocartasgui.modelo.juego;

import com.crisgon.autocartasgui.modelo.Carta;
import com.google.gson.annotations.Expose;

import java.util.ArrayList;

/**
 * Utilizo esta clase para empaquetar la información de las cartas de jugador y
 *  las cartas de maquina para posteriormente recogerlas con el Retrofit.
 *
 * Created by @cristhian-jg on 25/02/2020.
 */
public class Reparto {

    @Expose
    private ArrayList<Carta> cartasJugador;
    @Expose
    private ArrayList<Carta> cartasMaquina;

    public Reparto(ArrayList<Carta> cartasJugador, ArrayList<Carta> cartasMaquina) {
        this.cartasJugador = cartasJugador;
        this.cartasMaquina = cartasMaquina;
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

}
