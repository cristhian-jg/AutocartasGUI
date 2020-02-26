package com.crisgon.autocartasgui.modelo.juego;

/**
 * Clase que representa la caracteristica seleccionada por la CPU. No quería
 * precindir de esta clase pero he tenido dificultades para pasar Strings con el
 * Retrofit en Android.
 *
 * @author crist
 * <p>
 * Created by @cristhian-jg on 25/02/2020.
 */
public class CPUSelection {

    private String featureSelection;

    public CPUSelection(String featureSelection) {
        this.featureSelection = featureSelection;
    }

    public String getFeatureSelection() {
        return featureSelection;
    }

    public void setFeatureSelection(String featureSelection) {
        this.featureSelection = featureSelection;
    }

}
