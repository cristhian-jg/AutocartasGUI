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
import com.crisgon.autocartasgui.modelo.juego.Jugada;
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

    private Button btnMotor;
    private Button btnPotencia;
    private Button btnVelocidad;
    private Button btnCilindros;
    private Button btnRPM;
    private Button btnConsumo;

    private Button btnLanzar;

    private ArrayList<Carta> cartasJugador;
    private Carta cartaSeleccionada;
    private Caracteristica caracteristicaSeleccionada;

    private int numMano = 1;
    private boolean turnoJugador = false;
    private boolean turnoCPU = false;

    private String idSession;
    private int idGame;
    private String idCartaSeleccionadaCPU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mAPIService = APIUtils.getAPIService();

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            String idSession = extras.getString("idSession"); // retrieve the data using keyName
            int idGame = extras.getInt("idGame");

            sendRaffle(idSession, idGame);
        }

        rvCartasJugador = findViewById(R.id.rvCartasJugador);
        rvCartasJugador.setHasFixedSize(true);

        btnMotor = findViewById(R.id.btnMotor);
        btnPotencia = findViewById(R.id.btnPotencia);
        btnVelocidad = findViewById(R.id.btnVelocidad);
        btnCilindros = findViewById(R.id.btnCilindros);
        btnRPM = findViewById(R.id.btnRPM);
        btnConsumo = findViewById(R.id.btnConsumo);
        btnLanzar = findViewById(R.id.btnLanzar);

        btnLanzar.setOnClickListener(this);
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
                caracteristicaSeleccionada = Caracteristica.MOTOR;
                break;
            case R.id.btnPotencia:
                caracteristicaSeleccionada = Caracteristica.POTENCIA;
                break;
            case R.id.btnVelocidad:
                caracteristicaSeleccionada = Caracteristica.VELOCIDAD;
                break;
            case R.id.btnCilindros:
                caracteristicaSeleccionada = Caracteristica.CILINDROS;
                break;
            case R.id.btnRPM:
                caracteristicaSeleccionada = Caracteristica.REVOLUCIONES;
                break;
            case R.id.btnConsumo:
                caracteristicaSeleccionada = Caracteristica.CONSUMO;
                break;
            case R.id.btnLanzar:

                if (caracteristicaSeleccionada != null && caracteristicaSeleccionada != null) {
                    if (turnoJugador) {
                        sendPlayCard(idSession, idGame, cartaSeleccionada.getIdentificador(), caracteristicaSeleccionada, numMano);
                        sendReady(idSession, idGame, caracteristicaSeleccionada, numMano);
                        caracteristicaSeleccionada = null;
                        cartaSeleccionada = null;
                    }

                    if (turnoCPU) {
                        sendPlayCard(idSession, idGame, cartaSeleccionada.getIdentificador(), null, numMano);
                        sendReady(idSession, idGame, null, numMano);
                        caracteristicaSeleccionada = null;
                        cartaSeleccionada = null;
                    }

                    //sendCheck(idGame, idSession, cartaSeleccionada.getIdentificador(), idCartaSeleccionadaCPU,  );

                    numMano++;

                } else Toast.makeText(this, "Debes seleccionar la carta y la caracteristica...", Toast.LENGTH_SHORT).show();

            default:
                Log.i("JuegoActivity", "Algo salió mal");
                break;
        }
    }

    private void sendCheck(int idGame, String idSession, String idCartaJugador, String idCartaCPU, Caracteristica feature) {
        mAPIService.sendCheck(idGame, idSession, idCartaJugador, idCartaCPU, feature).enqueue(new Callback<Carta>() {
            @Override
            public void onResponse(Call<Carta> call, Response<Carta> response) {

            }

            @Override
            public void onFailure(Call<Carta> call, Throwable t) {

            }
        });

    }

    public void sendRaffle(final String idSession, final int idGame) {
        mAPIService.sendRaffle(idSession, idGame).enqueue(new Callback<Juego>() {
            @Override
            public void onResponse(Call<Juego> call, Response<Juego> response) {
                Log.i(TAG, "Ruffle leida" + response.body().toString());

                ArrayList<Carta> cartasJugador = response.body().getCartasJugador();

                setCartasJugador(cartasJugador);
                rellenarRecyclerCartas(cartasJugador);
                setIdGame(idGame);
                setIdSession(idSession);

                switch (response.code()) {
                    case 200:
                        Toast.makeText(JuegoActivity.this, "Tu empiezas eligiendo la caracteristica.", Toast.LENGTH_SHORT).show();
                        setTurnoJugador(true);

                        break;
                    case 202:
                        Toast.makeText(JuegoActivity.this, "La CPU deberá está eligiendo la caracteristica...", Toast.LENGTH_SHORT).show();
                        setTurnoCPU(true);
                        break;
                }
            }

            @Override
            public void onFailure(Call<Juego> call, Throwable t) {

            }
        });

    }

    public void rellenarRecyclerCartas(ArrayList<Carta> cartasJugador) {
        rvCartasJugador.setAdapter(new CartaAdapter(cartasJugador, this));
        rvCartasJugador.setLayoutManager(new GridLayoutManager(getApplicationContext(),6));
        rvCartasJugador.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    public void sendPlayCard(String idSession, int idGame, String idCard,
                             Caracteristica feature, int hand) {
        mAPIService.sendJugada(idSession, idGame, idCard, feature, hand).enqueue(new Callback<Jugada>() {
            @Override
            public void onResponse(Call<Jugada> call, Response<Jugada> response) {
                if (response.isSuccessful()) {
                        Log.i(TAG, "Se ha enviado el Post la jugada al servidor");
                }
            }

            @Override
            public void onFailure(Call<Jugada> call, Throwable t) {

            }
        });
    }

    public void sendReady(String idSession, int idGame, Caracteristica caracteristica, int hand) {
        mAPIService.sendReady(idSession, idGame, caracteristica, hand).enqueue(new Callback<Jugada>() {
            @Override
            public void onResponse(Call<Jugada> call, Response<Jugada> response) {
                if (response.isSuccessful()) {
                    setIdCartaSeleccionadaCPU(response.body().getIdCard());
                }
            }

            @Override
            public void onFailure(Call<Jugada> call, Throwable t) {

            }
        });
    }

    @Override
    public void onSelectedCarta(int position) {
        Toast.makeText(this, "HAS PULSADO EN UNA CARTA", Toast.LENGTH_SHORT).show();

        ArrayList<Carta> cartasJugador = getCartasJugador();
        cartaSeleccionada = cartasJugador.get(position);
    }

    public String getIdSession() {
        return idSession;
    }

    public void setIdSession(String idSession) {
        this.idSession = idSession;
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

    public boolean isTurnoJugador() {
        return turnoJugador;
    }

    public void setTurnoJugador(boolean turnoJugador) {
        this.turnoJugador = turnoJugador;
    }

    public boolean isTurnoCPU() {
        return turnoCPU;
    }

    public void setTurnoCPU(boolean turnoCPU) {
        this.turnoCPU = turnoCPU;
    }

    public String getIdCartaSeleccionadaCPU() {
        return idCartaSeleccionadaCPU;
    }

    public void setIdCartaSeleccionadaCPU(String idCartaSeleccionadaCPU) {
        this.idCartaSeleccionadaCPU = idCartaSeleccionadaCPU;
    }
}
