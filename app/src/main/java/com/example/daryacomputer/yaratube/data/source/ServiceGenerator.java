package com.example.daryacomputer.yaratube.data.source;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


import static com.example.daryacomputer.yaratube.data.source.Constant.BASE_URL;

public class ServiceGenerator {

    private static Retrofit retrofit = null;

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
