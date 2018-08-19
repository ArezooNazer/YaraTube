package com.example.daryacomputer.yaratube.data.source;

import com.example.daryacomputer.yaratube.data.model.Comment;
import com.example.daryacomputer.yaratube.data.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class ProductDetailCommentRepository {

    public void getCommentList(Product product, final ApiResult<List<Comment>> callback){


        ServiceGenerator.getInstance().create(ApiService.class)
                .getProductDetailCommentRequest(product.getId())
                .enqueue(new retrofit2.Callback<List<Comment>>() {
                    @Override
                    public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                         if(response.isSuccessful()){
                             List<Comment> comments = response.body();
                             if(comments != null){

                                 callback.onSuccess(comments);
                             }
                         }else{
                             callback.onError("connection error");
                         }
                    }

                    @Override
                    public void onFailure(Call<List<Comment>> call, Throwable t) {
                           callback.onError("Error");
                    }
                });
    }

}
