package com.example.daryacomputer.yaratube.data.source;

import com.example.daryacomputer.yaratube.data.model.Category;
import com.example.daryacomputer.yaratube.data.model.Homeitem;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("/store/16")
    Call<List<Homeitem>> getHomeItemListRequest();


    @GET("/category/16/463")
    Call<List<Category>> getCategoryListRequest();

}
