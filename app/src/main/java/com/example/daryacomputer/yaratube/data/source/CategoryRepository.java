package com.example.daryacomputer.yaratube.data.source;

import android.util.Log;

import com.example.daryacomputer.yaratube.data.model.Category;
import com.example.daryacomputer.yaratube.home.categoryPage.CategoryContract;

import java.util.List;

import javax.security.auth.callback.Callback;

import retrofit2.Call;
import retrofit2.Response;

public class CategoryRepository {

    private CategoryContract.View mView;
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
                    mView.ShowMessage("Error: response was not successful, try again.");
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                callback.onFailure();
            }
        });

    }

}
