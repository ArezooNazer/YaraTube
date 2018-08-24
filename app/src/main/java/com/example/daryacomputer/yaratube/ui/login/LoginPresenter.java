package com.example.daryacomputer.yaratube.ui.login;

import com.example.daryacomputer.yaratube.data.model.Login;
import com.example.daryacomputer.yaratube.data.source.ApiResult;
import com.example.daryacomputer.yaratube.data.source.LoginRepository;

public class LoginPresenter implements LoginContract.Presenter{

    private LoginRepository loginRepository;
    private LoginContract.View mView;


    public LoginPresenter(LoginContract.View mView) {
        this.mView = mView;
    }


    @Override
    public void sendPhoneNumber(final String mobileNum, String deviceId, String deviceModel, String deviceOs) {
        loginRepository = new LoginRepository();

        loginRepository.sendPhoneNumberRepository(mobileNum, deviceId, deviceModel, deviceOs, new ApiResult<Login>() {
            @Override
            public void onSuccess(Login result) {

            }

            @Override
            public void onError(String massage) {

                mView.showMassage("on Error");
            }
        });



    }
}
