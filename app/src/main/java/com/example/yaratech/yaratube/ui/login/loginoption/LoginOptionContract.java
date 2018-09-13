package com.example.yaratech.yaratube.ui.login.loginoption;

import com.example.yaratech.yaratube.ui.login.MainLoginContract;
import com.example.yaratech.yaratube.util.BaseView;

public interface LoginOptionContract {

    interface View extends BaseView{
        void googleLoginIsSuccessful();
    }


    interface Presenter {
        void sendGoogleToken(String tokenId,
                             String deviceId,
                             String deviceOs,
                             String deviceModel);
    }
}
