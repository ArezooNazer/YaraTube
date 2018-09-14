package com.example.yaratech.yaratube.ui.profile;

import com.example.yaratech.yaratube.data.entity.User;
import com.example.yaratech.yaratube.data.model.ProfileGetResponse;
import com.example.yaratech.yaratube.util.BaseView;

public interface ProfileContract {

    interface View extends BaseView{
        void showProfileField(ProfileGetResponse profileGetResponse);
        void showProfileFieldFromDb(User user);
    }

    interface Presenter{
        void getProfileFiledFromDb();
        void getProfileFieldFromServer(String token);
        void sendProfileField(String nickName, String birthDate, String gender, String token);
        User getUserInfo();
     }
}
