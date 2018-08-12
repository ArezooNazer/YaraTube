package com.example.daryacomputer.yaratube.data.source;

import com.example.daryacomputer.yaratube.data.model.HomeItem;

import java.util.List;

import javax.security.auth.callback.Callback;

import retrofit2.Call;
import retrofit2.Response;

public class HomeRepository {

    public void getHomeItems(final ApiResult<List<HomeItem>> callback){

        ServiceGenerator.getInstance().create(ApiService.class)
                .getHomeItemListRequest().enqueue(new retrofit2.Callback<List<HomeItem>>() {
            @Override
            public void onResponse(Call<List<HomeItem>> call, Response<List<HomeItem>> response) {

                if(response.isSuccessful()){
                    List<HomeItem> homeItems = response.body();
                    if(homeItems != null){

                    }

                }
            }

            @Override
            public void onFailure(Call<List<HomeItem>> call, Throwable t) {

            }
        });
    }
}
