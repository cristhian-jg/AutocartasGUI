package com.crisgon.autocartasgui.administrador;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.crisgon.autocartasgui.R;


public class AdminActivity extends AppCompatActivity implements View.OnClickListener {


    private Button btnGestionarCartas;
    private Button btnGestionarJugadores;
    private Button btnGestionarPartidas;
    private Button btnGestionarEstadisticas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        btnGestionarCartas = findViewById(R.id.btnGestionarCartas);
        btnGestionarJugadores = findViewById(R.id.btnGestionarJugadores);
        btnGestionarPartidas = findViewById(R.id.btnGestionarPartidas);
        btnGestionarEstadisticas = findViewById(R.id.btnGestionarEstadisticas);

        btnGestionarCartas.setOnClickListener(this);
        btnGestionarJugadores.setOnClickListener(this);
        btnGestionarPartidas.setOnClickListener(this);
        btnGestionarEstadisticas.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnGestionarCartas:
                Intent intent = new Intent(this, GestorCartasActivity.class);
                startActivity(intent);
                break;
            case R.id.btnGestionarJugadores:
                break;
            case R.id.btnGestionarPartidas:
                break;
            case R.id.btnGestionarEstadisticas:
                break;
            default:
                break;
        }

    }

}
