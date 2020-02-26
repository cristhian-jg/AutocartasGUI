package com.crisgon.autocartasgui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.crisgon.autocartasgui.adaptadores.EstadisticasAdapter;
import com.crisgon.autocartasgui.modelo.Estadistica;
import com.crisgon.autocartasgui.modelo.Partida;
import com.crisgon.autocartasgui.retrofit2.APIService;
import com.crisgon.autocartasgui.retrofit2.APIUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MenuActivity";

    private APIService mAPIService;

    private RecyclerView recyclerView;

    private Button btnIniciar;
    private Button btnPreferencias;
    private Button btnAcercaDe;

    private TextView tvIdSession;

    private String idSession;

    private int countPreference = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        tvIdSession = findViewById(R.id.tvIdSession);

        //Obtengo la id que se proporciono anteriormente
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String idSession = extras.getString("idSession"); // retrieve the data using keyName
            setIdSession(idSession);
            tvIdSession.setText(idSession);
        }

        mAPIService = APIUtils.getAPIService();

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);

        btnIniciar = findViewById(R.id.btnIniciar);
        btnPreferencias = findViewById(R.id.btnPreferencias);
        btnAcercaDe = findViewById(R.id.btnAcercaDe);

        // Lee las estadisticas actuales de la base de datos
        readEstadisticas();

        btnIniciar.setOnClickListener(this);
        btnPreferencias.setOnClickListener(this);
        btnAcercaDe.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Se actualizan las preferencias
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String user = preferences.getString("user-preference", "");

        if (countPreference !=0) {
            tvIdSession.setText(user);
        } preferences.edit().clear().commit();

        countPreference++;
    }

    /**
     * Obtiene las estadisitcas de la base de datos.
     */
    public void readEstadisticas() {
        mAPIService.readEstadisticas().enqueue(new Callback<List<Estadistica>>() {
            @Override
            public void onResponse(Call<List<Estadistica>> call, Response<List<Estadistica>> response) {
                Log.i(TAG, "Estadisticas leidas" + response.body().toString());
                rellenarRecyclerEstadisticas((ArrayList<Estadistica>) response.body());
            }

            @Override
            public void onFailure(Call<List<Estadistica>> call, Throwable t) {
                Log.e(TAG, "Error al enviar el post al API.");
            }
        });
    }

    /**
     * Rellena el recyclerview de estadisticas con el array recibido
     * @param estadisticas
     */
    public void rellenarRecyclerEstadisticas(ArrayList<Estadistica> estadisticas) {
        recyclerView.setAdapter(new EstadisticasAdapter(estadisticas));
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    public void setIdSession(String idSession) {
        this.idSession = idSession;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnIniciar:
                sendNuevaPartida(idSession);
                break;
            case R.id.btnPreferencias:
                Intent intent = new Intent(this, PreferencesActivity.class);
                startActivity(intent);
                break;
            case R.id.btnAcercaDe:
                Toast.makeText(this, "Esta aplicación ha sido creado por Cristhian González.", Toast.LENGTH_SHORT).show();
            default:
                break;
        }
    }

    /**
     * Crea una nueva partida.
     * @param idSession
     */
    public void sendNuevaPartida(String idSession) {
        mAPIService.sendNuevaPartida(idSession).enqueue(new Callback<Partida>() {
            @Override
            public void onResponse(Call<Partida> call, Response<Partida> response) {

                if (response.isSuccessful()) {
                    switch (response.code()) {
                        // Si la respuesta es 202 significa que el jugador tiene una partida no terminada
                        //por lo que hay que resetearla.
                        case 202:
                            String idSession = response.body().getJugador();
                            int idGame = response.body().getId();
                            sendResetPartida(idSession, idGame);
                            break;
                            // Si la respuesta es 200 significa que el jugador no tiene ninguna partida sin
                            // terminar y puede empezar una nueva
                        case 200:
                            Partida partida = response.body();
                            Intent intent = new Intent(getBaseContext(), JuegoActivity.class);
                            intent.putExtra("idGame", partida.getId());
                            intent.putExtra("idSession", partida.getJugador());
                            startActivity(intent);
                            break;
                    }

                }
            }

            @Override
            public void onFailure(Call<Partida> call, Throwable t) {

            }
        });
    }

    /**
     * Hace una llamada al endpoint de resetear partida y la termina, este vuelve a ejecutar sendNuevaPartida.
     * @param idSession
     * @param idGame
     */
    public void sendResetPartida(String idSession, int idGame) {
        mAPIService.sendResetPartida(idSession, idGame).enqueue(new Callback<Partida>() {
            @Override
            public void onResponse(Call<Partida> call, Response<Partida> response) {
                if (response.isSuccessful()) {
                    sendNuevaPartida(response.body().getJugador());
                }
            }

            @Override
            public void onFailure(Call<Partida> call, Throwable t) {
                Log.i(TAG, "No pudo invocarse el metodo");
            }
        });
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
