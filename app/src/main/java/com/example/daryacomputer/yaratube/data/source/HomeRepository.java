package com.example.daryacomputer.yaratube.data.source;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.daryacomputer.yaratube.data.model.Store;
import com.example.daryacomputer.yaratube.home.homePage.HomeContract;
import com.example.daryacomputer.yaratube.home.homePage.HomePageFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeRepository {

    private HomeContract.View mView;
    Context context ;
    public static String TAG = HomeRepository.class.getName();

    public void getHomeItems(final ApiResult<Store> callback){

        ServiceGenerator.getInstance().create(ApiService.class)
                .getStoreRequest().enqueue(new Callback<Store>() {
            @Override
            public void onResponse(Call<Store> call, Response<Store> response) {
                Log.i("homeItem", "onResponse: " + response.body().toString());
                if(response.isSuccessful()){

                    Store store = response.body();
//                    Toast.makeText(context.getApplicationContext(),"Connection error",Toast.LENGTH_LONG).show();
                    callback.onSuccess(store);
                }else{
                    Log.e(TAG, "get user onResponse ErrorBody ");
                    mView.ShowMassage("Error: can not show HomeItem list");
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<Store> call, Throwable t) {
                callback.onFailure();
            }
        });
    }
}


