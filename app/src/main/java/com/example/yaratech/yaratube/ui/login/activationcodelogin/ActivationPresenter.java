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

                if(result != null) {
                    updateUserEntity(result);
                    if( yaraDatabase.selectDao().getUserRecord().getToken() !=null) {
                        mView.showMessage("خوش آمدید");
                        mView.activationCodIsValid();
                    }
                }
            }

            @Override
            public void onError(String message) {
                mView.showMessage(message);
            }
        });

    }

    private void updateUserEntity(Register result) {

        User user = yaraDatabase.selectDao().getUserRecord();
        user.setToken(result.getToken());
        user.setFinoToken(result.getFinoToken());

//        if (result.getNickname() == null ) {
//            String userGeneratedName = stringGenerator();
//            user.setName(userGeneratedName);
//            user.setNickname(userGeneratedName);
//        } else {
//            user.setName(result.getNickname());
//            user.setNickname(result.getNickname());
//        }
        yaraDatabase.insertDao().updateUserInfo(user);
    }

}
