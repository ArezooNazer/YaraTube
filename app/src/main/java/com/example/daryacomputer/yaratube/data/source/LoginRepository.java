package com.example.daryacomputer.yaratube.data.source;

import android.util.Log;

import com.example.daryacomputer.yaratube.AddToTable;
import com.example.daryacomputer.yaratube.data.model.Login;
import com.example.daryacomputer.yaratube.data.model.Register;
import com.example.daryacomputer.yaratube.ui.login.LoginPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {

    public static String TAG = LoginRepository.class.getName();
    public static boolean isLogin = false;
    public static boolean sendIsSuccessful = false;

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

                            sendIsSuccessful = true;
                            Login result = response.body();
                            callback.onSuccess(result);

                        } else
                            callback.onError("دوباره تلاش کنید");
                    }

                    @Override
                    public void onFailure(Call<Login> call, Throwable t) {
                        Log.d("TAG", "onFailure");
                        callback.onError("دوباره تلاش کنید");

                    }
                });

    }


    public void sendActivationCodeRepository(String mobileNum,
                                             String deviceId,
                                             String verificationCode,
                                             String nickName,
                                             final ApiResult<Register> callback) {
        ServiceGenerator.getInstance().create(ApiService.class)
                .sendActivationCodeRequest(mobileNum, deviceId, verificationCode, nickName)
                .enqueue(new Callback<Register>() {
                    @Override
                    public void onResponse(Call<Register> call, Response<Register> response) {

                        if (response.isSuccessful()) {

                            isLogin = true;
                            Register result = response.body();
                            Log.d("TAG",  result.getToken());
                            callback.onSuccess(result);
                        } else
                            callback.onError("دوباره تلاش کنید");

                    }

                    @Override
                    public void onFailure(Call<Register> call, Throwable t) {
                        callback.onError("دوباره تلاش کنید");
                    }
                });


    }
}
