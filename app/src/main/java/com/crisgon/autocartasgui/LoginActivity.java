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

import com.crisgon.autocartasgui.administrador.AdminActivity;
import com.crisgon.autocartasgui.modelo.Jugador;
import com.crisgon.autocartasgui.retrofit2.APIService;
import com.crisgon.autocartasgui.retrofit2.APIUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "LoginActivity";

    private APIService mAPIService;

    private EditText etUsuario;
    private EditText etContrasenya;

    private Button btnIniciarSesion;
    private Button btnCrearCuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        etUsuario = findViewById(R.id.etUsuario);
        etContrasenya = findViewById(R.id.etContrasenya);

        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        btnCrearCuenta = findViewById(R.id.btnCrearCuenta);

        mAPIService = APIUtils.getAPIService();

        btnIniciarSesion.setOnClickListener(this);
        btnCrearCuenta.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnIniciarSesion:
                Log.i(TAG, "Login in was pressed");
                String nickname = etUsuario.getText().toString();
                String password = etContrasenya.getText().toString();

                // Lleva a la actividad de administracion
                if (nickname.equals("admin") && password.equals("admin")) {
                    Intent intent = new Intent(this, AdminActivity.class);
                    startActivity(intent);
                } else {
                    sendLogin(nickname, password);

                }
                break;
            case R.id.btnCrearCuenta:
                Log.i(TAG, "Create account was pressed");
                // Lleva a la actividad de registro
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
            default:
                Log.e(TAG, "Something went wrong...");
                break;
        }

    }

    /**
     * Comprueba que el usuario y la contraseña introducidos es correcta.
     * @param nickname
     * @param password
     */
    public void sendLogin(String nickname, String password) {
        mAPIService.loginJugador(nickname, password).enqueue(new Callback<Jugador>() {
            @Override
            public void onResponse(Call<Jugador> call, Response<Jugador> response) {

                if (response.isSuccessful()) {
                    Intent intent = new Intent(getBaseContext(), MenuActivity.class);
                    intent.putExtra("idSession", response.body().getNickname());
                    startActivity(intent);
                    Log.i(TAG, "Post enviado al API." + response.body().toString());
                } else {
                    switch (response.code()) {
                        case 401:
                            Toast.makeText(getBaseContext(), "El usuario o la contraseña no son correctos", Toast.LENGTH_SHORT).show();
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
