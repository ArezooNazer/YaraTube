package com.example.yaratech.yaratube.ui.profile.pickavatar;

import com.example.yaratech.yaratube.util.BaseView;

import okhttp3.MultipartBody;

public interface PickAvatarContract {

    interface View extends BaseView{
        void dismissDialog();
    }

    interface Presenter{
        void sendUserAvatarToServer(MultipartBody.Part body, String token);
    }
}
