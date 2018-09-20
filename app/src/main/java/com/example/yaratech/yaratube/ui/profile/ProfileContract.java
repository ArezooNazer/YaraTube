package com.example.yaratech.yaratube.ui.profile;

import com.example.yaratech.yaratube.data.entity.User;
import com.example.yaratech.yaratube.data.model.ProfileGetResponse;
import com.example.yaratech.yaratube.util.BaseView;

import okhttp3.MultipartBody;

public interface ProfileContract {

    interface View extends BaseView {
        void showProfileFieldFromServer(ProfileGetResponse profileGetResponse);

        void showProfileFieldFromDb(User user);
    }

    interface Presenter {
        void getProfileFiledFromDb();

        void getProfileFieldFromServer(String token);

        void sendProfileField(String nickName, String birthDate, String gender, String token);

        void sendUserAvatarToServer(MultipartBody.Part body, String token);

        User getUserInfo();

        void logOut();
    }

    interface Listener {
        void photoSelectedListener(String imagePath);
    }
}
