package com.example.yaratech.yaratube.data.source;

import android.util.Log;

import com.example.yaratech.yaratube.data.model.GoogleLogin;
import com.example.yaratech.yaratube.data.model.Login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GoogleLoginRepository {

    public static String TAG = LoginRepository.class.getName();

    public void sendGoogleTokenRepository(String googleToken,
                                          String deviceId,
                                          String deviceOs,
                                          String deviceModel,
                                          final ApiResult<GoogleLogin> callback) {

        ServiceGenerator.getInstance().create(ApiService.class)
                .sendGoogleTokenRequest(googleToken, deviceId, deviceOs, deviceModel)
                .enqueue(new Callback<GoogleLogin>() {
                    @Override
                    public void onResponse(Call<GoogleLogin> call, Response<GoogleLogin> response) {

                        if (response.isSuccessful()) {

                            GoogleLogin result = response.body();
                            callback.onSuccess(result);

                        } else
                            callback.onError("دوباره تلاش کنید");
                    }

                    @Override
                    public void onFailure(Call<GoogleLogin> call, Throwable t) {
                        Log.d("TAG", "onFailure");
                        callback.onError("دوباره تلاش کنید");

                    }
                });
    }
}
