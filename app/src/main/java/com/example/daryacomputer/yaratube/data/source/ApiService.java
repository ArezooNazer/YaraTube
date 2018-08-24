package com.example.daryacomputer.yaratube.data.source;

import com.example.daryacomputer.yaratube.data.model.Category;
import com.example.daryacomputer.yaratube.data.model.Comment;
import com.example.daryacomputer.yaratube.data.model.Login;
import com.example.daryacomputer.yaratube.data.model.Product;
import com.example.daryacomputer.yaratube.data.model.Store;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

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

    //productDetailPage
    @GET("/product/{product_id}")
    Call<Product> getProductDetailRequest(@Path("product_id") int productId);

    //productDetailPage comments
    @GET("/comment/{product_id}")
    Call<List<Comment>> getProductDetailCommentRequest(@Path("product_id") int productId);

    //Send mobile number for login
    @POST("/mobile_login_step1/16")
    @FormUrlEncoded
    Call<Login> sendPhoneNumberRequest(@Field("mobile") String mobileNum,
                                       @Field("device_id") String deviceId,
                                       @Field("device_model") String deviceModel,
                                       @Field("device_os") String deviceOs);

}
