package com.crisgon.autocartasgui.administrador;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.crisgon.autocartasgui.ICartaListener;
import com.crisgon.autocartasgui.R;
import com.crisgon.autocartasgui.adaptadores.CartaAdapter;
import com.crisgon.autocartasgui.modelo.Carta;
import com.crisgon.autocartasgui.retrofit2.APIService;
import com.crisgon.autocartasgui.retrofit2.APIUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GestorCartasActivity extends AppCompatActivity implements View.OnClickListener {

    private APIService mAPIService;

    private RecyclerView rvGestorCartas;

    private Button btnCrearCarta;
    private Button btnActualizarCarta;
    private Button btnBorrarCarta;

    private EditText etIdentificadorCarta;
    private EditText etMarcaCarta;
    private EditText etModeloCarta;
    private EditText etMotorCarta;
    private EditText etPotenciaCarta;
    private EditText etVelocidadCarta;
    private EditText etCilindrosCarta;
    private EditText etRevolucionesCarta;
    private EditText etConsumoCarta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestor_cartas);

        mAPIService = APIUtils.getAPIService();

        rvGestorCartas = findViewById(R.id.rvGestorCartas);
        rvGestorCartas.setHasFixedSize(true);

        btnCrearCarta = findViewById(R.id.btnCrearCarta);
        btnActualizarCarta = findViewById(R.id.btnActualizarCarta);
        btnBorrarCarta = findViewById(R.id.btnBorrarCarta);

        etIdentificadorCarta = findViewById(R.id.etIdentificadorCarta);
        etMarcaCarta = findViewById(R.id.etMarcaCarta);
        etModeloCarta = findViewById(R.id.etModeloCarta);
        etMotorCarta = findViewById(R.id.etMotorCarta);
        etPotenciaCarta = findViewById(R.id.etPotenciaCarta);
        etVelocidadCarta = findViewById(R.id.etVelocidadCarta);
        etCilindrosCarta = findViewById(R.id.etCilindrosCarta);
        etRevolucionesCarta = findViewById(R.id.etRevolucionesCarta);
        etConsumoCarta = findViewById(R.id.etConsumoCarta);

        readCartas();

        btnCrearCarta.setOnClickListener(this);
        btnActualizarCarta.setOnClickListener(this);
        btnBorrarCarta.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        String identificador = etIdentificadorCarta.getText().toString();
        String marca = etMarcaCarta.getText().toString();
        String modelo = etModeloCarta.getText().toString();
        int motor = Integer.parseInt(etMotorCarta.getText().toString());
        int potencia = Integer.parseInt(etPotenciaCarta.getText().toString());
        int velocidad = Integer.parseInt(etVelocidadCarta.getText().toString());
        int cilindros = Integer.parseInt(etCilindrosCarta.getText().toString());
        int revoluciones = Integer.parseInt(etRevolucionesCarta.getText().toString());
        double consumo = Double.parseDouble(etConsumoCarta.getText().toString());

        switch (v.getId()) {
            case R.id.btnCrearCarta:
                sendCarta(identificador, marca, modelo,  motor, potencia, velocidad, cilindros, revoluciones, consumo);
                break;
            case R.id.btnActualizarCarta:
                break;
            case R.id.btnBorrarCarta:
                deleteCarta(identificador);
                break;
            default:
                break;
        }

    }


    private void readCartas() {
        mAPIService.readCartas().enqueue(new Callback<List<Carta>>() {
            @Override
            public void onResponse(Call<List<Carta>> call, Response<List<Carta>> response) {
                ArrayList<Carta> cartas = (ArrayList<Carta>) response.body();
                rellenarRecylerCartas(cartas);
            }

            @Override
            public void onFailure(Call<List<Carta>> call, Throwable t) {

            }
        });
    }

    private void sendCarta(String identificador, String marca, String modelo, int motor,
                           int potencia, int velocidad, int cilindros,
                           int revoluciones, double consumo) {
        mAPIService.sendCarta(identificador, marca, modelo, motor, potencia, velocidad, cilindros,
                revoluciones, consumo).enqueue(new Callback<Carta>() {
            @Override
            public void onResponse(Call<Carta> call, Response<Carta> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(GestorCartasActivity.this, "Se ha añadido la carta correctamente.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Carta> call, Throwable t) {
                Toast.makeText(GestorCartasActivity.this, "Algo salió mal...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //private void updateCarta();

    private void deleteCarta(String identificador) {
        mAPIService.deleteCarta(identificador).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, Response<Response> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(GestorCartasActivity.this, "Se ha eliminado correctamente.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(GestorCartasActivity.this, "Algo salió mal...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void rellenarRecylerCartas(ArrayList<Carta> cartas) {
        rvGestorCartas.setAdapter(new CartaAdapter(cartas, new ICartaListener() {
            @Override
            public void onSelectedCarta(int position) {
            }
        }));
        rvGestorCartas.setLayoutManager(new GridLayoutManager(this, 4));
    }
}
