package com.example.daryacomputer.yaratube.data.source;

import com.example.daryacomputer.yaratube.data.model.Category;
import com.example.daryacomputer.yaratube.data.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class ProductGridRepository {

    public void getProductList(Category category , final ApiResult<List<Product>> callback){

        ServiceGenerator.getInstance().create(ApiService.class)
                .getProductListRequest(category.getId()).enqueue(new retrofit2.Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                if(response.isSuccessful()){

                    List<Product> products = response.body();
                    if(products != null){
                        callback.onSuccess(products);
                    }
                }else {
                    callback.onError("connection error");
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                        callback.onError("response error");
            }
        });

    }
}
