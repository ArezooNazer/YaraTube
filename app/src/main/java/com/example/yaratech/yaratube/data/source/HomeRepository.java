package com.example.yaratech.yaratube.data.source;

import android.util.Log;

import com.example.yaratech.yaratube.data.model.Store;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeRepository {


    public static String TAG = HomeRepository.class.getName();

    public void getHomeItems(final ApiResult<Store> callback){

        ServiceGenerator.getInstance().create(ApiService.class)
                .getStoreRequest().enqueue(new Callback<Store>() {
            @Override
            public void onResponse(Call<Store> call, Response<Store> response) {

                Log.i("homeItem", "onResponse: " + response.body().toString());
                if(response.isSuccessful()){

                    Store store = response.body();
                    callback.onSuccess(store);

                }else{
                    Log.e(TAG, "get user onResponse ErrorBody ");
                    callback.onError("Connection Error");
                }
            }

            @Override
            public void onFailure(Call<Store> call, Throwable t) {
                callback.onError("No response");
            }
        });
    }
}


