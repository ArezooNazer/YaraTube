package com.example.yaratech.yaratube.ui.profile;

import android.util.Log;

import com.example.yaratech.yaratube.data.entity.User;
import com.example.yaratech.yaratube.data.model.Profile;
import com.example.yaratech.yaratube.data.source.ApiResult;
import com.example.yaratech.yaratube.data.source.ProfileRepository;

import static com.example.yaratech.yaratube.MainActivity.yaraDatabase;

public class ProfilePresenter implements ProfileContract.Presenter{

    public static String ProfilePresenter =ProfilePresenter.class.getName();
    private ProfileRepository profileRepository;
    private ProfileContract.View mView;

    public ProfilePresenter(ProfileContract.View mView) {
        this.mView = mView;
        profileRepository= new ProfileRepository();
    }

    @Override
    public void sendProfileAvatar(String Avatar) {

    }

    @Override
    public void sendProfileField(final String nickName, final String dateOfBirth, final String gender, final String token) {

        profileRepository.sendProfileFieldRepository(nickName, gender, dateOfBirth, token, new ApiResult<Profile>() {
            @Override
            public void onSuccess(Profile result) {

                updateUserEntity(nickName,dateOfBirth,gender);
                mView.readUserInfoAndSetProfileFields();
                mView.showMessage("تغییرات با موفقیت ذخیره شد");

            }

            @Override
            public void onError(String massage) {
                mView.showMessage("عملیات ناموفق، دوباره تلاش کنید");
            }
        });
    }

    private void updateUserEntity(String nickName, String dateOfBirth ,String gender){

        User user = yaraDatabase.selectDao().getUserRecord();
        user.setName(nickName);
        user.setNickname(nickName);
        user.setGender(gender);
        user.setBirthDate(dateOfBirth);
        yaraDatabase.insertDao().updateUserInfo(user);

        Log.d("ProfilePresenter", "nickname : " + user.getName() + " gender : " + user.getGender() + " dateOfBirth : " + user.getBirthDate() );
    }
}
