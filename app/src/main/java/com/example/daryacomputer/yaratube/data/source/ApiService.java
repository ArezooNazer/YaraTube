package com.example.daryacomputer.yaratube.data.source;

import android.support.v7.widget.CardView;

import com.example.daryacomputer.yaratube.data.model.Category;
import com.example.daryacomputer.yaratube.data.model.Comment;
import com.example.daryacomputer.yaratube.data.model.Product;
import com.example.daryacomputer.yaratube.data.model.Store;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    //homepage (homeitem + headeritem) list
    @GET("/store/16")
    Call<Store> getStoreRequest();

    //categoryPage (all category list)
    @GET("/category/16/463")
    Call<List<Category>> getCategoryListRequest();

    // category grid (all the products of a category item)
    @GET("/listproducts/{category_id}")
    Call<List<Product>> getProductListRequest(@Path("category_id") int categoryId);

    @GET("/comment/{product_id}")
    Call<List<Comment>> getProductDetailCommentRequest(@Path("product_id") int productId);

}
