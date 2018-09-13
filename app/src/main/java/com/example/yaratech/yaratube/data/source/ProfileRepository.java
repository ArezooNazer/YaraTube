package com.example.yaratech.yaratube.data.source;

import com.example.yaratech.yaratube.data.model.Profile;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileRepository {

    public void sendProfileFieldRepository(String nickname, String gender, String dateOfBirth, String token, final ApiResult<Profile> callback){

        ServiceGenerator.getInstance().create(ApiService.class)
                .sendProfileFieldRequest(nickname, gender, dateOfBirth, token )
                .enqueue(new Callback<Profile>() {
                    @Override
                    public void onResponse(Call<Profile> call, Response<Profile> response) {
                        if(response.isSuccessful()) {
                            Profile result = response.body();
                            callback.onSuccess(result);
                        }
                        else
                            callback.onError("تغییرات ثبت نشد، دوباره تلاش کنید.");

                    }

                    @Override
                    public void onFailure(Call<Profile> call, Throwable t) {
                            callback.onError("خطا، دوباره تلاش کنید.");
                    }
                });
    }
}
