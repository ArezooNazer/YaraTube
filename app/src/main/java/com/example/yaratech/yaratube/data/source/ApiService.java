package com.example.yaratech.yaratube.data.source;

import com.example.yaratech.yaratube.data.model.Category;
import com.example.yaratech.yaratube.data.model.Comment;
import com.example.yaratech.yaratube.data.model.GoogleLogin;
import com.example.yaratech.yaratube.data.model.Login;
import com.example.yaratech.yaratube.data.model.Product;
import com.example.yaratech.yaratube.data.model.Profile;
import com.example.yaratech.yaratube.data.model.ProfileGetResponse;
import com.example.yaratech.yaratube.data.model.Register;
import com.example.yaratech.yaratube.data.model.SendComment;
import com.example.yaratech.yaratube.data.model.Store;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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
    // @GET("/listproducts/{category_id}?limit=10&offset=offset")
    @GET("/listproducts/{category_id}")
    Call<List<Product>> getProductListRequest(@Path("category_id") int categoryId,
                                              @Query("limit") int limit,
                                              @Query("offset") int offset);

    //productDetailPage
    @GET("/product/{product_id}?device_os=ios")
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

    //send login activation code and receive registration token
    @POST("mobile_login_step2/16")
    @FormUrlEncoded
    Call<Register> sendActivationCodeRequest(@Field("mobile") String mobileNum,
                                             @Field("device_id") String deviceId,
                                             @Field("verification_code") String verificationCode,
                                             @Field("nickname") String nickname);


    //google login
    @POST("/login_google/16")
    @FormUrlEncoded
    Call<GoogleLogin> sendGoogleTokenRequest(@Field("token_id") String googleToken,
                                             @Field("device_id") String deviceId,
                                             @Field("device_os") String deviceOs,
                                             @Field("device_model") String deviceModel);

    @POST("/comment/{product_id}")
    @FormUrlEncoded
    Call<SendComment> sendCommentRequest(@Field("title") String title,
                                         @Field("score") int score,
                                         @Field("comment_text") String commentText,
                                         @Path("product_id") int productId,
                                         @Header("Authorization") String token);

    //just send avatar
    @Multipart
    @POST("profile")
    Call<Profile> sendProfileAvatarRequest( @Part MultipartBody.Part avatarFile,
                                            @Header("Authorization") String token);


    //send other profile fields
    @POST("profile")
    @FormUrlEncoded
    Call<Profile> sendProfileFieldRequest(@Field("nickname") String nickname,
                                          @Field("gender") Object gender,
                                          @Field("date_of_birth") Object dateOfBirth,
                                          @Header("Authorization") String token);

    @GET("profile")
    Call<ProfileGetResponse> getProfileFieldsRequest(@Header("Authorization") String token);

}
