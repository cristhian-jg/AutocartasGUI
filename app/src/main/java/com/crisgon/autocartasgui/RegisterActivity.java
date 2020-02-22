package com.crisgon.autocartasgui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.crisgon.autocartasgui.modelo.Jugador;
import com.crisgon.autocartasgui.retrofit2.APIService;
import com.crisgon.autocartasgui.retrofit2.APIUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "RegisterActivity";

    private APIService mAPIService;

    private EditText etNombre;
    private EditText etNickname;
    private EditText etPassword;

    private Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mAPIService = APIUtils.getAPIService();

        etNombre = findViewById(R.id.etNombre);
        etNickname = findViewById(R.id.etNickname);
        etPassword = findViewById(R.id.etPassword);

        btnRegistrar = findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnRegistrar:
                String nombre = etNombre.getText().toString();
                String nickname = etNickname.getText().toString();
                String password = etPassword.getText().toString();
                sendRegister(nickname, nombre, password);
                break;
        }
    }

    public void sendRegister(String nickname, String nombre, String password) {
        mAPIService.registerJugador(nickname, nombre, password).enqueue(new Callback<Jugador>() {
            @Override
            public void onResponse(Call<Jugador> call, Response<Jugador> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "Post enviado al API." + response.body().toString());
                    Toast.makeText(RegisterActivity.this, "Se ha registrado correctamente.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getBaseContext(), MenuActivity.class);
                    startActivity(intent);
                } else {
                    switch (response.code()) {
                        case 401:
                            Toast.makeText(getBaseContext(), "Usuario existente", Toast.LENGTH_SHORT).show();
                            break;
                        case 404:
                            Toast.makeText(getBaseContext(), "Not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
                            Toast.makeText(getBaseContext(), "Server broken", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(getApplicationContext(), "Unknown error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<Jugador> call, Throwable t) {
                Log.e(TAG, "Error al enviar el post al API.");
            }
        });
    }
}
