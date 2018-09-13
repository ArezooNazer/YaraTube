package com.example.yaratech.yaratube.ui.profile;

import com.example.yaratech.yaratube.util.BaseView;

public interface ProfileContract {

    interface View extends BaseView{
        void readUserInfoAndSetProfileFields();
    }

    interface Presenter{
        void sendProfileAvatar(String Avatar);
        void sendProfileField(String nickName, String birthDate, String gender, String token);
     }
}
