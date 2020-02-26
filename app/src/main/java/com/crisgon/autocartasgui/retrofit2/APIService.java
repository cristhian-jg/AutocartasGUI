package com.crisgon.autocartasgui.retrofit2;

import com.crisgon.autocartasgui.modelo.Carta;
import com.crisgon.autocartasgui.modelo.Estadistica;
import com.crisgon.autocartasgui.modelo.Jugador;
import com.crisgon.autocartasgui.modelo.Partida;
import com.crisgon.autocartasgui.modelo.juego.CPUSelection;
import com.crisgon.autocartasgui.modelo.juego.Reparto;
import com.crisgon.autocartasgui.modelo.juego.HandResult;

import java.sql.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Interfaz que define las conexiones con la APIREST.
 *
 * Created by @cristhian-jg on 18/02/2020.
 */
public interface APIService {

    // - - - - - - - - - - - - - Login y registro - - - - - - - - - - - - - -

    @POST("Autocartas/rest/init/login")
    @FormUrlEncoded
    Call<Jugador> loginJugador(@Field("nickname") String nickname,
                               @Field("password") String password);

    @POST("Autocartas/rest/init/register")
    @FormUrlEncoded
    Call<Jugador> registerJugador(@Field("nickname") String nickname,
                                  @Field("nombre") String nombre,
                                  @Field("password") String password);

    // - - - - - - - - - - - - - - - PARTIDA - - - - - - - - - - - - - - - - -

    @POST("Autocartas/rest/init/newgame")
    @FormUrlEncoded
    Call<Partida> sendNuevaPartida(@Field("idSession") String idSession);

    @POST("Autocartas/rest/init/reset")
    @FormUrlEncoded
    Call<Partida> sendResetPartida(@Field("idSession") String idSession, @Field("idGame") int idGame);

    @POST("Autocartas/rest/init/raffle")
    @FormUrlEncoded
    Call<Reparto> sendRaffle(@Field("idSession") String idSession,
                             @Field("idGame") int idGame);

    @POST("Autocartas/rest/init/playcard")
    @FormUrlEncoded
    Call<HandResult> sendJugada(@Field("idSession") String idSession,
                                @Field("idGame") int idGame,
                                @Field("idCard") String idCard,
                                @Field("feature") String feature,
                                @Field("hand") int hand);

    @POST("Autocartas/rest/init/ready")
    @FormUrlEncoded
    Call<CPUSelection> sendReady(@Field("idSession") String idSession,
                                 @Field("idGame") int idGame);

    // - - - - - - - - - - - - GET, POST, PUT, DELETE de Cartas. - - - - - - - - - - - -

    @GET("Autocartas/rest/cartas/getcards")
    Call<List<Carta>> readCartas();

    @GET("Autocartas/rest/cartas/getcard")
    Call<Carta> readCarta(@Query("identificador") String identificador);

    @POST("Autocartas/rest/cartas/create")
    @FormUrlEncoded
    Call<Carta> sendCarta(@Field("identificador") String identificador,
                          @Field("marca") String marca,
                          @Field("modelo") String modelo,
                          @Field("motor") int motor,
                          @Field("potencia") int potenica,
                          @Field("velocidad") int velocidad,
                          @Field("cilindros") int cilindros,
                          @Field("revoluciones") int revoluciones,
                          @Field("consumo") double consumo);

    // TODO FALTA METODO PUT.
    @PUT
    @FormUrlEncoded
    Call<Carta> updateCarta();

    @DELETE("Autocartas/rest/cartas/delete")
    Call<Response> deleteCarta(@Query("identificador") String identificador);

    // - - - - - - - - - - - - GET, POST, PUT, DELETE de Jugadores. - - - - - - - - - - - -

    @GET("Autocartas/rest/jugadores/getplayers")
    Call<List<Jugador>> readJugadores();

    // TODO FALTA METODO GET UN JUGADOR.
    @GET
    Call<Jugador> readJugador();

    @POST
    @FormUrlEncoded
    Call<Jugador> sendJugador(@Field("nickname") String nickname,
                              @Field("nombre") String nombre,
                              @Field("password") String password);

    // TODO FALTA METODO PUT
    @PUT
    @FormUrlEncoded
    Call<Jugador> updateJugador();

    @DELETE
    Call<Response> deteleJugador(@Query("nickname") String nickname);


    // - - - - - - - - - - - - GET, POST, PUT, DELETE de Estadisticas. - - - - - - - - - - - -

    @GET("Autocartas/rest/estadisticas/getstats")
    Call<List<Estadistica>> readEstadisticas();

    @GET
    Call<Partida> readEstadistica();


    @POST("Autocartas/rest/estadisticas/create")
    @FormUrlEncoded
    Call<Estadistica> sendEstadistica(@Field("jugador") String jugador,
                                      @Field("partida") int partida,
                                      @Field("ganadas") int ganadas,
                                      @Field("perdidas") int perdidas,
                                      @Field("empatadas") int empatadas);

    // TODO FALTA METODO PUT
    @PUT
    @FormUrlEncoded
    Call<Estadistica> updateEstadistica();

    // TODO FALTA METODO DELETE
    @DELETE
    Call<Response> deleteEstadistica();

    // - - - - - - - - - - - - GET, POST, PUT, DELETE de Partidas. - - - - - - - - - - - -

    @GET("Autocartas/rest/partidas/getgames")
    Call<List<Partida>> readPartidas();

    // TODO FALTA METODO GET PARTIDA
    @GET
    Call<Partida> readPartida();

    @POST
    @FormUrlEncoded
    Call<Partida> sendPartida(@Field("id") int id,
                              @Field("jugador") String jugador,
                              @Field("ganada") boolean ganada,
                              @Field("terminada") boolean terminada,
                              @Field("fecha") Date fecha);

    // TODO FALTA METODO PUT
    @PUT
    @FormUrlEncoded
    Call<Partida> updatePartida();

    @DELETE("Autocartas/rest/partidas/delete")
    Call<Response> deletePartida(@Field("id") int id);
}
