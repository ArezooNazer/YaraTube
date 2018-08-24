package com.example.daryacomputer.yaratube.data.source;

import android.util.Log;

import com.example.daryacomputer.yaratube.data.model.Login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {

    public static String TAG = LoginRepository.class.getName();

    public void sendPhoneNumberRepository(String mobileNum,
                                          String deviceId,
                                          String deviceModel,
                                          String deviceOs,
                                          final ApiResult<Login> callback) {

        ServiceGenerator.getInstance().create(ApiService.class)
                .sendPhoneNumberRequest(mobileNum, deviceId, deviceModel, deviceOs)
                .enqueue(new Callback<Login>() {
                    @Override
                    public void onResponse(Call<Login> call, Response<Login> response) {

                        if (response.isSuccessful()) {
                            Login result = response.body();
                            callback.onSuccess(result);
                            Log.d("TAG", "sms sent" + result.toString());

                        } else if (response.code() == 400) {
                            Log.d("TAG", "Store id is not valid");
                        } else if (response.code() == 406) {
                            Log.d("TAG", "incomplete parameters");
                        } else if (response.code() == 504) {
                            Log.d("TAG", "sms send failed");
                        }
                    }

                    @Override
                    public void onFailure(Call<Login> call, Throwable t) {
                        Log.d("TAG", "onFailure");
                        callback.onError("onFailure");

                    }
                });
    }
}
