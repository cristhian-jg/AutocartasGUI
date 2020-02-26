package com.crisgon.autocartasgui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.crisgon.autocartasgui.adaptadores.CartaAdapter;
import com.crisgon.autocartasgui.modelo.Carta;
import com.crisgon.autocartasgui.modelo.Estadistica;
import com.crisgon.autocartasgui.modelo.juego.CPUSelection;
import com.crisgon.autocartasgui.modelo.juego.HandResult;
import com.crisgon.autocartasgui.modelo.juego.Reparto;
import com.crisgon.autocartasgui.retrofit2.APIService;
import com.crisgon.autocartasgui.retrofit2.APIUtils;

import java.util.ArrayList;
import java.util.List;

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

    private TextView tvGanadasJugador;
    private TextView tvGanadasCPU;

    private TextView tvNicknameJugador;

    private ArrayList<Carta> cartasJugador;
    private ArrayList<Carta> cartasMaquina;

    private TextView tvIdCartaSeleccionada;
    private TextView tvMotorCartaSeleccionada;
    private TextView tvPotenciaCartaSeleccionada;
    private TextView tvVelocidadCartaSeleccionada;
    private TextView tvConsumoCartaSeleccionada;
    private TextView tvRevolucionesCartaSeleccionada;
    private TextView tvCilindrosCartaSeleccionada;

    private TextView tvIdCartaSeleccionadaCPU;
    private TextView tvMotorCartaSeleccionadaCPU;
    private TextView tvPotenciaCartaSeleccionadaCPU;
    private TextView tvVelocidadCartaSeleccionadaCPU;
    private TextView tvConsumoCartaSeleccionadaCPU;
    private TextView tvRevolucionesCartaSeleccionadaCPU;
    private TextView tvCilindrosCartaSeleccionadaCPU;

    private TextView tvCaracteristicaSeleccionada;
    private TextView tvIndicadorMano;

    private Carta cartaSeleccionada;
    private String caracteristicaSeleccionada;

    private int numMano = 0;

    private boolean turnoCPU = false;
    private boolean turnoJugador = false;

    private String idSession;
    private int idGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mAPIService = APIUtils.getAPIService();

        tvNicknameJugador = findViewById(R.id.tvNicknameJugador);

        // Obtengo la sesion y el juego
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            idSession = extras.getString("idSession");
            idGame = extras.getInt("idGame");
            tvNicknameJugador.setText(idSession);
            sendRaffle(idSession, idGame);

        }

        tvIdCartaSeleccionada = findViewById(R.id.tvIdCartaSeleccionada);
        tvMotorCartaSeleccionada = findViewById(R.id.tvMotorCartaSeleccionada);
        tvPotenciaCartaSeleccionada = findViewById(R.id.tvPotenciaCartaSeleccionada);
        tvVelocidadCartaSeleccionada = findViewById(R.id.tvVelocidadCartaSeleccionada);
        tvConsumoCartaSeleccionada = findViewById(R.id.tvConsumoCartaSeleccionada);
        tvRevolucionesCartaSeleccionada = findViewById(R.id.tvRevolucionesCartaSeleccionada);
        tvCilindrosCartaSeleccionada = findViewById(R.id.tvCilindrosCartaSeleccionada);


        tvIdCartaSeleccionadaCPU = findViewById(R.id.tvIdCartaSeleccionadaCPU);
        tvMotorCartaSeleccionadaCPU = findViewById(R.id.tvMotorCartaSeleccionadaCPU);
        tvPotenciaCartaSeleccionadaCPU = findViewById(R.id.tvPotenciaCartaSeleccionadaCPU);
        tvVelocidadCartaSeleccionadaCPU = findViewById(R.id.tvVelocidadCartaSeleccionadaCPU);
        tvConsumoCartaSeleccionadaCPU = findViewById(R.id.tvConsumoCartaSeleccionadaCPU);
        tvRevolucionesCartaSeleccionadaCPU = findViewById(R.id.tvRevolucionesCartaSeleccionadaCPU);
        tvCilindrosCartaSeleccionadaCPU = findViewById(R.id.tvCilindrosCartaSeleccionadaCPU);

        tvCaracteristicaSeleccionada = findViewById(R.id.tvCaracteristicaSeleccionada);
        tvIndicadorMano = findViewById(R.id.tvIndicadorMano);


        rvCartasJugador = findViewById(R.id.rvCartasJugador);
        rvCartasJugador.setHasFixedSize(true);

        tvGanadasCPU = findViewById(R.id.tvGanadasCPU);
        tvGanadasJugador = findViewById(R.id.tvGanadasJugador);

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

        // Si es el turno del jugadores los botones cambiaran la caracteristica, en caso contrario el
        //jugador no podra hacer nada
        switch (v.getId()) {
            case R.id.btnMotor:
                if (turnoJugador) {
                    caracteristicaSeleccionada = "MOTOR";
                }
                break;
            case R.id.btnPotencia:
                if (turnoJugador) {
                    caracteristicaSeleccionada = "POTENCIA";
                }
                break;
            case R.id.btnVelocidad:
                if (turnoJugador) {
                    caracteristicaSeleccionada = "VELOCIDAD";
                }
                break;
            case R.id.btnCilindros:
                if (turnoJugador) {
                    caracteristicaSeleccionada = "CILINDROS";
                }
                break;
            case R.id.btnRPM:
                if (turnoJugador) {
                    caracteristicaSeleccionada = "REVOLUCIONES";
                }
                break;
            case R.id.btnConsumo:
                if(turnoJugador) {
                    caracteristicaSeleccionada = "CONSUMO";
                }
                break;

                // Al pulsar lanzar empieza la jugada
            case R.id.btnLanzar:

                // Siempre y el usuario haya seleccionado una carta o la caracteristica este seleccionada hará lo siguiente
                if (caracteristicaSeleccionada != null && cartaSeleccionada != null) {
                    if (numMano < 6) {
                        if (numMano == 5) {
                            Toast.makeText(this, "Esta es la ultima jugada, haz tu mejor elección...", Toast.LENGTH_SHORT).show();
                            btnLanzar.setText("TERMINAR");
                        }

                        //Enviara la jugada al servidor
                        enviarJugada();

                        if (turnoJugador) {
                            turnoJugador = false;
                            turnoCPU = true;
                        }

                        if (turnoCPU) {
                            sendReady(idSession, idGame);
                            turnoJugador = true;
                            turnoCPU = false;
                        }


                    } else {
                        Intent i = new Intent(this, MenuActivity.class);
                        sendEstadistica(idSession, idGame, Integer.parseInt(tvGanadasJugador.getText().toString()), Integer.parseInt(tvGanadasCPU.getText().toString()), 0);
                        startActivity(i);
                    }
                }

                if (caracteristicaSeleccionada == null) {
                    Toast.makeText(this, "Debes seleccionar la caracteristica", Toast.LENGTH_SHORT).show();
                }

                if (cartaSeleccionada == null) {
                    Toast.makeText(this, "Debes seleccionar tu carta...", Toast.LENGTH_SHORT).show();
                }

                caracteristicaSeleccionada = null;
                cartaSeleccionada = null;

                if (turnoCPU) {
                    sendReady(idSession, idGame);
                }

                break;
            default:
                Log.i("JuegoActivity", "Algo salió mal");
                break;
        }
        if (v.getId() != R.id.btnLanzar) {
            tvCaracteristicaSeleccionada.setText(caracteristicaSeleccionada);
        }
    }

    /**
     * Envia la jugada al servidor, aumenta la mano y remueve las carta seleccionada por el jugador,
     * indica la mano y refresca las cartas del jugador
     */
    public void enviarJugada() {
        sendPlayCard(idSession, idGame, cartaSeleccionada.getIdentificador(), caracteristicaSeleccionada, numMano);
        numMano++;
        cartasJugador.remove(cartaSeleccionada);
        rellenarRecyclerCartas(cartasJugador);
        tvIndicadorMano.setText(String.valueOf(numMano));
    }

    /**
     * Al iniciar la partida se reparten las cartas y el turno
     * @param idSession
     * @param idGame
     */
    public void sendRaffle(final String idSession, final int idGame) {
        mAPIService.sendRaffle(idSession, idGame).enqueue(new Callback<Reparto>() {
            @Override
            public void onResponse(Call<Reparto> call, Response<Reparto> response) {
                Log.i(TAG, "Ruffle leida" + response.body().toString());

                cartasJugador = (ArrayList<Carta>) response.body().getCartasJugador();
                cartasMaquina = (ArrayList<Carta>) response.body().getCartasMaquina();

                rellenarRecyclerCartas(cartasJugador);

                switch (response.code()) {
                    case 200:
                        Toast.makeText(JuegoActivity.this, "Empiezas eligiendo la caracteristica.", Toast.LENGTH_SHORT).show();
                        turnoJugador = true;
                        numMano++;
                        break;
                    case 202:
                        Toast.makeText(JuegoActivity.this, "La CPU empieza eligiendo la caracteristica...", Toast.LENGTH_SHORT).show();
                        turnoCPU = true;
                        numMano++;
                        break;
                }
            }

            @Override
            public void onFailure(Call<Reparto> call, Throwable t) {
                Log.e(TAG, "No se ha podido enviar el POST al API.");
            }
        });

    }

    // Al terminar la partida se crean las estadisitcas de esta
    public void sendEstadistica(String idSession, int idGame, int ganadas, int perdidas, int empatadas) {
        mAPIService.sendEstadistica(idSession, idGame, ganadas, perdidas, empatadas).enqueue(new Callback<Estadistica>() {
            @Override
            public void onResponse(Call<Estadistica> call, Response<Estadistica> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "Se han creado las estadisticas.");
                }
            }

            @Override
            public void onFailure(Call<Estadistica> call, Throwable t) {

            }
        });

    }

    //Rellenar el recyclerview de cartas y desabilita el tacto (deja de ser scrollable)
    public void rellenarRecyclerCartas(ArrayList<Carta> cartasJugador) {
        rvCartasJugador.setAdapter(new CartaAdapter(cartasJugador, this));
        rvCartasJugador.setLayoutManager(new GridLayoutManager(getApplicationContext(), 6));
        rvCartasJugador.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    /**
     * Envia la jugada al servidor y este responde con un resultado
     * @param idSession
     * @param idGame
     * @param idCard
     * @param caracteristica
     * @param hand
     */
    public void sendPlayCard(String idSession, int idGame, String idCard, String caracteristica, int hand) {
        mAPIService.sendJugada(idSession, idGame, idCard, caracteristica, hand).enqueue(new Callback<HandResult>() {
            @Override
            public void onResponse(Call<HandResult> call, Response<HandResult> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "Se ha enviado el Post al servidor");
                    //Define el marcador
                    definirCartaCPU(response.body().getCartaSeleccionadaCPU());
                    definirMarcador(response.body().getMarcadorJugador(), response.body().getMarcadorCPU());
                }
            }

            @Override
            public void onFailure(Call<HandResult> call, Throwable t) {
                Log.e(TAG, "No se ha podido enviar el POST al API.");
            }
        });
    }

    /**
     * Indica que es el turno de la CPU por lo que le toca elegir la caracterisitca.
     * @param idSession
     * @param idGame
     */
    public void sendReady(String idSession, int idGame) {
        mAPIService.sendReady(idSession, idGame).enqueue(new Callback<CPUSelection>() {
            @Override
            public void onResponse(Call<CPUSelection> call, Response<CPUSelection> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "Se ha podido enviar el POST al API.");
                    Toast.makeText(JuegoActivity.this, "Ahora es el turno de CPU y ha seleccionado la caracteristica: " + response.body().getFeatureSelection(), Toast.LENGTH_LONG).show();
                    tvCaracteristicaSeleccionada.setText(response.body().getFeatureSelection());
                    caracteristicaSeleccionada = response.body().getFeatureSelection();
                }
            }

            @Override
            public void onFailure(Call<CPUSelection> call, Throwable t) {
                Log.e(TAG, "No se ha podido enviar el POST al API.");
            }
        });
    }

    /**
     * Rellena los valores con la carta seleccionada con la CPU.
     * @param carta
     */
    public void definirCartaCPU(Carta carta) {
        tvIdCartaSeleccionadaCPU.setText("" + carta.getIdentificador());
        tvMotorCartaSeleccionadaCPU.setText("" + carta.getMotor());
        tvCilindrosCartaSeleccionadaCPU.setText("" + carta.getCilindros());
        tvRevolucionesCartaSeleccionadaCPU.setText("" + carta.getRevoluciones());
        tvConsumoCartaSeleccionadaCPU.setText("" + carta.getConsumo());
        tvVelocidadCartaSeleccionadaCPU.setText("" + carta.getVelocidad());
        tvPotenciaCartaSeleccionadaCPU.setText("" + carta.getPotencia());
    }

    /**
     * Define el marcador de la partida
     * @param puntosJugador
     * @param puntosCPU
     */
    public void definirMarcador(int puntosJugador, int puntosCPU) {
        tvGanadasJugador.setText(String.valueOf(puntosJugador));
        tvGanadasCPU.setText(String.valueOf(puntosCPU));

        if (turnoCPU) {
            Toast.makeText(this, "Ahora tu rival seleccionará la caracteristica...", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Define la carta seleccionada por la CPU.
     * @param position
     */
    @Override
    public void onSelectedCarta(int position) {
        cartaSeleccionada = cartasJugador.get(position);
        tvIdCartaSeleccionada.setText("" + cartaSeleccionada.getIdentificador());
        tvMotorCartaSeleccionada.setText("" + cartaSeleccionada.getMotor());
        tvCilindrosCartaSeleccionada.setText("" + cartaSeleccionada.getCilindros());
        tvRevolucionesCartaSeleccionada.setText("" + cartaSeleccionada.getRevoluciones());
        tvConsumoCartaSeleccionada.setText("" + cartaSeleccionada.getConsumo());
        tvVelocidadCartaSeleccionada.setText("" + cartaSeleccionada.getVelocidad());
        tvPotenciaCartaSeleccionada.setText("" + cartaSeleccionada.getPotencia());
    }

}
