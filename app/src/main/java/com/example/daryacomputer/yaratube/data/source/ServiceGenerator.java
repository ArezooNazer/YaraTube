package com.example.daryacomputer.yaratube.data.source;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServiceGenerator {

    @GET("/category/16/463")
    Call<List<Object>> getCategoryList();
}
