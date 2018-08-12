package com.example.daryacomputer.yaratube.data.source;


import com.example.daryacomputer.yaratube.data.model.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class ServiceGenerator {

    private static Retrofit retrofit = null;
    public static String BASE_URL = "https://api.vasapi.click/";

    public static Retrofit getInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
