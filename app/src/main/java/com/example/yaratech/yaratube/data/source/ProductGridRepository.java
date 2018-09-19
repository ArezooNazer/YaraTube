package com.example.yaratech.yaratube.data.source;

import com.example.yaratech.yaratube.data.model.Category;
import com.example.yaratech.yaratube.data.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

import static com.example.yaratech.yaratube.data.source.Constant.LIMIT;

public class ProductGridRepository {

    public void getProductList(Category category, int offset, final ApiResult<List<Product>> callback) {

        ServiceGenerator.getInstance().create(ApiService.class)
                .getProductListRequest(category.getId(), LIMIT, offset).enqueue(new retrofit2.Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                if (response.isSuccessful()) {

                    List<Product> products = response.body();
                    if (products != null) {
                        callback.onSuccess(products);
                    }
                } else {
                    callback.onError("اتصال دستگاه خود را به اینترنت چک کنید");
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                callback.onError("اتصال دستگاه خود را به اینترنت چک کنید");
            }
        });

    }
}
