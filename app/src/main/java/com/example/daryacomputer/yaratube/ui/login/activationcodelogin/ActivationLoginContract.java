package com.example.daryacomputer.yaratube.ui.login.activationcodelogin;

import com.example.daryacomputer.yaratube.ui.login.MainLoginContract;

public interface ActivationLoginContract {

    interface View extends MainLoginContract.View{
        void activationCodIsValid();
    }

    interface Presenter {

        void sendActivationCode(String mobileNum,
                                String deviceId,
                                String verificationCode,
                                String nickName);
    }
}
