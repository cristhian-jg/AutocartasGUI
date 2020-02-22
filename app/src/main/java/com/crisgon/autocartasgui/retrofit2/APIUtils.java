package com.crisgon.autocartasgui.retrofit2;

import com.crisgon.autocartasgui.retrofit2.APIRestClient;
import com.crisgon.autocartasgui.retrofit2.APIService;

/**
 * Created by @cristhian-jg on 18/02/2020.
 */
public class APIUtils {

    private APIUtils() {
    }

    public static final String BASE_URL = "http://192.168.20.45:8080";

    public static APIService getAPIService() {
        return APIRestClient.getInstance(BASE_URL).getRetrofit().create(APIService.class);
    }
}
