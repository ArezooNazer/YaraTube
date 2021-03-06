package com.example.yaratech.yaratube.data.source;

import android.util.Log;

import com.example.yaratech.yaratube.data.model.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class CategoryRepository {

    public static String TAG = CategoryRepository.class.getName();

    public void getCategoryList(final ApiResult<List<Category>> callback) {

        ServiceGenerator.getInstance().create(ApiService.class)
                .getCategoryListRequest().enqueue(new retrofit2.Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {

                if (response.isSuccessful()) {
                    List<Category> categoryList = response.body();

                    if (categoryList != null) {
                        callback.onSuccess(categoryList);
                    }
                }else{
                    Log.e(TAG, "get user onResponse ErrorBody ");
                    callback.onError("اتصال دستگاه خود را به اینترنت چک کنید");
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                callback.onError("اتصال دستگاه خود را به اینترنت چک کنید");
            }
        });

    }

}
