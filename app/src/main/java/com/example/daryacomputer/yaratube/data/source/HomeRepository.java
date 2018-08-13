package com.example.daryacomputer.yaratube.data.source;

import android.util.Log;

import com.example.daryacomputer.yaratube.data.model.Homeitem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class HomeRepository {

    public static String TAG = HomeRepository.class.getName();

    public void getHomeItems(final ApiResult<List<Homeitem>> callback){

        ServiceGenerator.getInstance().create(ApiService.class)
                .getHomeItemListRequest().enqueue(new retrofit2.Callback<List<Homeitem>>() {
            @Override
            public void onResponse(Call<List<Homeitem>> call, Response<List<Homeitem>> response) {

                if(response.isSuccessful()){
                    Log.i("homeItem", "onResponse: " + response.body().get(0).getTitle());
                    List<Homeitem> homeItems = response.body();
                    if(homeItems != null){
                        callback.onSuccess(homeItems);
                    }

                }else{
                    Log.e(TAG, "get user onResponse ErrorBody ");
                }
            }

            @Override
            public void onFailure(Call<List<Homeitem>> call, Throwable t) {
                callback.onFailure();
            }
        });
    }
}
