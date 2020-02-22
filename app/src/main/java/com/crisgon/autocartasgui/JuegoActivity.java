package com.crisgon.autocartasgui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.crisgon.autocartasgui.adaptadores.CartaAdapter;
import com.crisgon.autocartasgui.modelo.Carta;
import com.crisgon.autocartasgui.modelo.juego.Juego;
import com.crisgon.autocartasgui.retrofit2.APIService;
import com.crisgon.autocartasgui.retrofit2.APIUtils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JuegoActivity extends AppCompatActivity implements View.OnClickListener, ICartaListener {

    private static final String TAG = "JuegoActivity";

    private APIService mAPIService;

    private RecyclerView rvCartasJugador;

    private ArrayList<Carta> cartasJugador;

    private Button btnMotor;
    private Button btnPotencia;
    private Button btnVelocidad;
    private Button btnCilindros;
    private Button btnRPM;
    private Button btnConsumo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        rvCartasJugador = findViewById(R.id.rvCartasJugador);
        rvCartasJugador.setHasFixedSize(true);

        mAPIService = APIUtils.getAPIService();

        btnMotor = findViewById(R.id.btnMotor);
        btnPotencia = findViewById(R.id.btnPotencia);
        btnVelocidad = findViewById(R.id.btnVelocidad);
        btnCilindros = findViewById(R.id.btnCilindros);
        btnRPM = findViewById(R.id.btnRPM);
        btnConsumo = findViewById(R.id.btnConsumo);

        readJuego();

        btnMotor.setOnClickListener(this);
        btnPotencia.setOnClickListener(this);
        btnVelocidad.setOnClickListener(this);
        btnCilindros.setOnClickListener(this);
        btnRPM.setOnClickListener(this);
        btnConsumo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnMotor:
                break;
            case R.id.btnPotencia:
                break;
            case R.id.btnVelocidad:
                break;
            case R.id.btnCilindros:
                break;
            case R.id.btnRPM:
                break;
            case R.id.btnConsumo:
                break;
            default:
                Log.i("JuegoActivity", "Algo salió mal");
                break;
        }
    }

    public void readJuego() {
        mAPIService.readJugada("Iskahn", 1).enqueue(new Callback<Juego>() {
            @Override
            public void onResponse(Call<Juego> call, Response<Juego> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "Jugada leida" + response.body().toString());
                    showResponse(response.body().getCartasJugador());
                }
            }

            @Override
            public void onFailure(Call<Juego> call, Throwable t) {
                Log.i(TAG, "Algo salió mal");
            }
        });
    }

    public void showResponse(ArrayList<Carta> cartasJugador) {
        rvCartasJugador.setAdapter(new CartaAdapter(cartasJugador, this));
        rvCartasJugador.setLayoutManager(new GridLayoutManager(getApplicationContext(),6));
        rvCartasJugador.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    @Override
    public void onSelectedCarta(int position) {
        Toast.makeText(this, "HAS PULSADO EN UNA CARTA", Toast.LENGTH_SHORT).show();
    }
}
