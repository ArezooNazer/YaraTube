package com.example.yaratech.yaratube.ui.login.loginoption;

import android.util.Log;

import com.example.yaratech.yaratube.data.entity.User;
import com.example.yaratech.yaratube.data.model.GoogleLogin;
import com.example.yaratech.yaratube.data.source.ApiResult;
import com.example.yaratech.yaratube.data.source.GoogleLoginRepository;

import static com.example.yaratech.yaratube.MainActivity.yaraDatabase;

public class LoginOptionPresenter implements LoginOptionContract.Presenter {

String TOKEN = LoginOptionPresenter.class.getName();
    private GoogleLoginRepository googleLoginRepository;
    private LoginOptionContract.View mView;

    public LoginOptionPresenter(LoginOptionContract.View mView) {
        this.mView = mView;
        googleLoginRepository = new GoogleLoginRepository();
    }

    @Override
    public void sendGoogleToken(String googleToken, final String deviceId, final String deviceOs, final String deviceModel) {

        googleLoginRepository.sendGoogleTokenRepository(googleToken, deviceId, deviceOs, deviceModel, new ApiResult<GoogleLogin>() {
            @Override
            public void onSuccess(GoogleLogin result) {

                User user = yaraDatabase.selectDao().getUserRecord();
                user.setDeviceId(deviceId);
                user.setDeviceModel(deviceModel);
                user.setDeviceOs(deviceOs);
                user.setNickname(result.getNickname());
                user.setToken(result.getToken());
                Log.d("TOKEN", "onSuccess() called with: result = [" + result.getToken() + "]");
                yaraDatabase.insertDao().updateUserInfo(user);

                 mView.showMessage("خوش آمدید");
            }

            @Override
            public void onError(String massage) {
                mView.showMessage("ارسال با موفقیت انجام نشد، دوباره تلاش کنید");
            }
        });

    }
}
