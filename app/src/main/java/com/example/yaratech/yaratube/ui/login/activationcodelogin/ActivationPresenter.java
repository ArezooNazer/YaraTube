package com.example.yaratech.yaratube.ui.login.activationcodelogin;

import com.example.yaratech.yaratube.data.entity.User;
import com.example.yaratech.yaratube.data.model.Register;
import com.example.yaratech.yaratube.data.source.ApiResult;
import com.example.yaratech.yaratube.data.source.LoginRepository;

import static com.example.yaratech.yaratube.MainActivity.yaraDatabase;

public class ActivationPresenter implements ActivationContract.Presenter {

    private LoginRepository loginRepository;
    private ActivationContract.View mView;

    public ActivationPresenter(ActivationContract.View mView) {
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
               yaraDatabase.insertDao().updateUserInfo(user);

               mView.activationCodIsValid();

            }

            @Override
            public void onError(String massage) {

                mView.showMessage("ارسال با موفقیت انجام نشد، دوباره تلاش کنید");
            }
        });

    }
}
