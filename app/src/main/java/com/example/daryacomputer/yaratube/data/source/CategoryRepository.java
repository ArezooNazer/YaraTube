package com.example.daryacomputer.yaratube.data.source;

import com.example.daryacomputer.yaratube.data.model.Category;

import java.util.List;

import javax.security.auth.callback.Callback;

import retrofit2.Call;
import retrofit2.Response;

public class CategoryRepository {

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

                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                callback.onFailure();
            }
        });

    }

}
