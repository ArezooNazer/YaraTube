package com.example.yaratech.yaratube.ui.login.loginoption;

import android.util.Log;

import com.example.yaratech.yaratube.data.entity.User;
import com.example.yaratech.yaratube.data.model.GoogleLogin;
import com.example.yaratech.yaratube.data.source.ApiResult;
import com.example.yaratech.yaratube.data.source.GoogleLoginRepository;

import static com.example.yaratech.yaratube.MainActivity.yaraDatabase;
import static com.example.yaratech.yaratube.util.StringGenerator.stringGenerator;

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

        mView.showProgressBar();
        googleLoginRepository.sendGoogleTokenRepository(googleToken, deviceId, deviceOs, deviceModel, new ApiResult<GoogleLogin>() {
            @Override
            public void onSuccess(GoogleLogin result) {

                updateUserEntity(result, deviceId, deviceModel, deviceOs);
                mView.hideProgressBar();
                mView.showMessage("خوش آمدید");
                mView.googleLoginIsSuccessful();
            }

            @Override
            public void onError(String message) {
                mView.showMessage(message);
            }
        });

    }


    private void updateUserEntity(GoogleLogin result, String deviceId, String deviceModel, String deviceOs) {

        User user = yaraDatabase.selectDao().getUserRecord();
        user.setDeviceId(deviceId);
        user.setDeviceModel(deviceModel);
        user.setDeviceOs(deviceOs);
        user.setToken(result.getToken());
        user.setName(result.getNickname());
        user.setNickname(result.getNickname());
        yaraDatabase.insertDao().updateUserInfo(user);
        Log.d("TOKEN", "user nickname = [" + result.getNickname() + " " + user.getName() + "]");
        Log.d("TOKEN", "date of birth = [" +  user.getBirthDate() + "]");
    }


}
