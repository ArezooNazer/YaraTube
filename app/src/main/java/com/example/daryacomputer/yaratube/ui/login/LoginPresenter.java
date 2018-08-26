package com.example.daryacomputer.yaratube.ui.login;

import com.example.daryacomputer.yaratube.data.entity.Token;
import com.example.daryacomputer.yaratube.data.model.Login;
import com.example.daryacomputer.yaratube.data.model.Register;
import com.example.daryacomputer.yaratube.data.source.ApiResult;
import com.example.daryacomputer.yaratube.data.source.LoginRepository;

import static com.example.daryacomputer.yaratube.MainActivity.yaraDatabase;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginRepository loginRepository;
    private LoginContract.View mView;
    private LoginContract.onChildButtonClickListener mListener;


    public LoginPresenter(LoginContract.View mView) {
        this.mView = mView;
        loginRepository = new LoginRepository();
    }


    @Override
    public void sendPhoneNumber(final String mobileNum, String deviceId, String deviceModel, String deviceOs) {

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

    @Override
    public void sendActivationCode(String mobileNum, String deviceId, String verificationCode, String nickName) {

        loginRepository.sendActivationCodeRepository(mobileNum, deviceId, verificationCode, nickName, new ApiResult<Register>() {
            @Override
            public void onSuccess(Register result) {

                Token token = new Token();
                token.setToken(result.getToken());
//                DatabaseUtil.addToken(yaraDatabase ,token);
                yaraDatabase.insertDao().saveToken(token);
            }

            @Override
            public void onError(String massage) {
                mView.showMassage("on Error");
            }
        });

    }
}
