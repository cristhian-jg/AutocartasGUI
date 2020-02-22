package com.crisgon.autocartasgui.retrofit2;

import com.crisgon.autocartasgui.modelo.Carta;
import com.crisgon.autocartasgui.modelo.Estadistica;
import com.crisgon.autocartasgui.modelo.Jugador;
import com.crisgon.autocartasgui.modelo.Partida;
import com.crisgon.autocartasgui.modelo.juego.Juego;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by @cristhian-jg on 18/02/2020.
 */
public interface APIService {

    /**
     * GET de cartas, jugadores, partidas y estadisticas
     */
    @GET("Autocartas/rest/cartas/read")
    Call<List<Carta>> readCartas();

    @GET("Autocartas/rest/jugadores/read")
    Call<List<Jugador>> readJugadores();

    @GET("Autocartas/rest/partidas/read")
    Call<List<Partida>> readPartidas();

    @GET("Autocartas/rest/estadisticas/read")
    Call<List<Estadistica>> readEstadisticas();

    /**
     * GET de carta, jugador, partida y estadistica
     */
    @GET("Autocartas/rest/partidas")
    Call<Carta> readCarta();

    @GET
    Call<Jugador> readJugador();

    @GET
    Call<Partida> readPartida();

    @GET
    Call<Estadistica> readEstadistica();


    @POST("Autocartas/rest/iniciar/login")
    @FormUrlEncoded
    Call<Jugador> loginJugador(@Field("nickname") String nickname,
                               @Field("password") String password);

    @POST("Autocartas/rest/iniciar/login")
    @FormUrlEncoded
    Call<Jugador> loginJugador(@Body Jugador jugador);

    @POST("Autocartas/rest/iniciar/register")
    @FormUrlEncoded
    Call<Jugador> registerJugador(@Field("nickname") String nickname,
                                  @Field("nombre") String nombre,
                                  @Field("password") String password);

    @GET("Autocartas/rest/iniciar/raffle")
    Call<Juego> readJugada(@Query("idSession") String idSession,
                           @Query("idGame") int idGame);
}
