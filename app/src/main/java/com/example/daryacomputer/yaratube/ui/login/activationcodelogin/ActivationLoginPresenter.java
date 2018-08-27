package com.example.daryacomputer.yaratube.ui.login.activationcodelogin;

import com.example.daryacomputer.yaratube.data.entity.User;
import com.example.daryacomputer.yaratube.data.model.Register;
import com.example.daryacomputer.yaratube.data.source.ApiResult;
import com.example.daryacomputer.yaratube.data.source.LoginRepository;

import static com.example.daryacomputer.yaratube.MainActivity.yaraDatabase;

public class ActivationLoginPresenter implements ActivationLoginContract.Presenter {

    private LoginRepository loginRepository;
    private ActivationLoginContract.View mView;

    public ActivationLoginPresenter(ActivationLoginContract.View mView) {
        loginRepository = new LoginRepository();
        this.mView = mView;
    }

    @Override
    public void sendActivationCode(final String mobileNum, final String deviceId, String verificationCode, String nickName) {

        loginRepository.sendActivationCodeRepository(mobileNum, deviceId, verificationCode, nickName, new ApiResult<Register>() {
            @Override
            public void onSuccess(Register result) {

               User user = yaraDatabase.selectDao().getUserRecord();
               user.setFinoToken(result.getFinoToken());
               user.setNickname(result.getNickname());
               user.setToken(result.getToken());
               user.setMessage(result.getMessage());
               user.setError(result.getError());
               yaraDatabase.insertDao().updateUserInfo(user);

               mView.activationCodIsValid();

            }

            @Override
            public void onError(String massage) {

                mView.showMassage("ارسال با موفقیت انجام نشد، ارتباط با اینترنت بر قرار نیست.");
            }
        });

    }
}
