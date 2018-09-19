package com.example.yaratech.yaratube.data.source;

import com.example.yaratech.yaratube.data.model.Product;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailRepository {


    public void getProductDetail(Product product, final ApiResult<Product> callback) {

        ServiceGenerator.getInstance().create(ApiService.class)
                .getProductDetailRequest(product.getId())
                .enqueue(new Callback<Product>() {
                    @Override
                    public void onResponse(Call<Product> call, Response<Product> response) {

                        if (response.isSuccessful()) {
                            Product responseProduct = response.body();
                            callback.onSuccess(responseProduct);
                        } else {
                            callback.onError("خطا در نمایش اطلاعات");
                        }
                    }

                    @Override
                    public void onFailure(Call<Product> call, Throwable t) {
                        callback.onError("اتصال دستگاه خود را به اینترنت چک کنید");
                    }
                });
    }
}
