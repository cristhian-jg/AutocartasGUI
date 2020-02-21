package com.crisgon.autocartasgui.retrofit2;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by @cristhian-jg on 17/02/2020.
 */
public class APIRestClient {

    private static final String TAG = "APIRestClient";
    private static APIRestClient instance;
    private Retrofit retrofit;

    private APIRestClient() {
    }

    private APIRestClient(String baseUrl) {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static APIRestClient getInstance(String baseUrl) {
        if (instance == null) {
            synchronized (APIRestClient.class) {
                if (instance == null) {
                    instance = new APIRestClient(baseUrl);
                }
            }
        }
        return instance;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
