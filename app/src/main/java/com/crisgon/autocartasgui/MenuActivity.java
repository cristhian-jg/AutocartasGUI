package com.crisgon.autocartasgui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.crisgon.autocartasgui.adaptadores.EstadisticasAdapter;
import com.crisgon.autocartasgui.modelo.Estadistica;
import com.crisgon.autocartasgui.modelo.juego.Juego;
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
    private ArrayList<Estadistica> estadisticas;

    private Button btnIniciar;
    private TextView tvIdSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        tvIdSession = findViewById(R.id.tvIdSession);
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            String idSession = extras.getString("idSession"); // retrieve the data using keyName
            Log.e(TAG, idSession);
            asignarIdSession(idSession);
        }



        mAPIService = APIUtils.getAPIService();

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);

        tvIdSession = findViewById(R.id.tvIdSession);

        btnIniciar = findViewById(R.id.btnIniciar);

        readEstadisticas();

        btnIniciar.setOnClickListener(this);
    }

    public void readEstadisticas() {
        mAPIService.readEstadisticas().enqueue(new Callback<List<Estadistica>>() {
            @Override
            public void onResponse(Call<List<Estadistica>> call, Response<List<Estadistica>> response) {
                ArrayList<Estadistica> estadisticas = new ArrayList<>();
                Log.i(TAG, "Estadisticas leidas" + response.body().toString());
                //for (int i = 0; i < response.body().size(); i++) {
                //    estadisticas.add(response.body().get(i));
                //}
                showResponse((ArrayList<Estadistica>) response.body());
            }

            @Override
            public void onFailure(Call<List<Estadistica>> call, Throwable t) {
                Log.e(TAG, "Error al enviar el post al API.");
            }
        });
    }

    public void showResponse(ArrayList<Estadistica> estadisticas) {
        recyclerView.setAdapter(new EstadisticasAdapter(estadisticas));
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    public void asignarIdSession(String idSession) {
        tvIdSession.setText(""+idSession);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnIniciar:
                Intent intent = new Intent(this, JuegoActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
