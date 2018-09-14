package com.example.yaratech.yaratube.data.source;

import com.example.yaratech.yaratube.data.model.Profile;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadAvatarRepository {

    public void uploadUserAvatar(MultipartBody.Part body, String token, final ApiResult<Profile> callback) {

        ServiceGenerator.getInstance().create(ApiService.class)
                .sendProfileAvatarRequest(body, token)
                .enqueue(new Callback<Profile>() {
                    @Override
                    public void onResponse(Call<Profile> call, Response<Profile> response) {

                        if (response.isSuccessful()) {
                            callback.onSuccess(response.body());
                        } else
                            callback.onError("error");
                    }

                    @Override
                    public void onFailure(Call<Profile> call, Throwable t) {
                        callback.onError("on error");
                    }
                });


    }
}
