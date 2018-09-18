package com.example.yaratech.yaratube.data.source;

import com.example.yaratech.yaratube.data.model.Comment;
import com.example.yaratech.yaratube.data.model.Product;
import com.example.yaratech.yaratube.data.model.SendComment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentRepository {

    public void getCommentList(Product product, final ApiResult<List<Comment>> callback) {

        ServiceGenerator.getInstance().create(ApiService.class)
                .getProductDetailCommentRequest(product.getId())
                .enqueue(new retrofit2.Callback<List<Comment>>() {
                    @Override
                    public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                        if (response.isSuccessful()) {
                            List<Comment> comments = response.body();
                            if (comments != null) {

                                callback.onSuccess(comments);
                            }
                        } else {
                            callback.onError("connection error");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Comment>> call, Throwable t) {
//                        callback.onError("Error");
                    }
                });
    }


    public void sendCommentRepo(String title,
                                int score,
                                String commentText,
                                int productId,
                                String token,
                                final ApiResult<SendComment> callback) {

        ServiceGenerator.getInstance().create(ApiService.class)
                .sendCommentRequest(title, score, commentText, productId, token)
                .enqueue(new Callback<SendComment>() {
                    @Override
                    public void onResponse(Call<SendComment> call, Response<SendComment> response) {

                        if (response.isSuccessful())
                            callback.onSuccess(response.body());
                        else
                            callback.onError("دوباره تلاش کنید");
                    }

                    @Override
                    public void onFailure(Call<SendComment> call, Throwable t) {
                        callback.onError("دوباره تلاش کنید");
                    }
                });
    }

}
