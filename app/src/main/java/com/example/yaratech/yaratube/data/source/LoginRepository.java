package com.example.yaratech.yaratube.data.source;

import android.util.Log;

import com.example.yaratech.yaratube.data.model.Login;
import com.example.yaratech.yaratube.data.model.Register;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.yaratech.yaratube.MainActivity.yaraDatabase;

public class LoginRepository {

    public static String TAG = LoginRepository.class.getName();
    public static boolean isLogin = false;

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

                        } else
                            callback.onError("دوباره تلاش کنید: " + response.message());
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
                            Log.d("TAG", result.getToken());
                            callback.onSuccess(result);
                        } else
                            callback.onError("دوباره تلاش کنید: " + response.message());

                    }

                    @Override
                    public void onFailure(Call<Register> call, Throwable t) {
                        callback.onError("دوباره تلاش کنید");
                    }
                });


    }

    public static boolean isLogin() {
        Log.d(TAG, "isLogin() called : " + yaraDatabase.selectDao().selectToken());

        if (yaraDatabase.selectDao().selectToken() != null)
            return true;
        else
            return false;
    }
}
