package com.example.yaratech.yaratube.ui.profile;

import android.util.Log;

import com.example.yaratech.yaratube.data.entity.User;
import com.example.yaratech.yaratube.data.model.Profile;
import com.example.yaratech.yaratube.data.model.ProfileGetResponse;
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
    public void getProfileFiledFromDb() {
        mView.showProgressBar();
        User user = yaraDatabase.selectDao().getUserRecord();
        if(user != null)
            mView.showProfileFieldFromDb(user);
        mView.hideProgressBar();

    }

    @Override
    public void getProfileFieldFromServer(String token) {

        mView.showProgressBar();
        profileRepository.getProfileFieldsRepository(token, new ApiResult<ProfileGetResponse>() {
            @Override
            public void onSuccess(ProfileGetResponse result) {

                mView.showProfileField(result);
                mView.hideProgressBar();
            }

            @Override
            public void onError(String massage) {
                mView.showMessage("خطا در نمایش اطلاعات");
            }
        });
    }

    @Override
    public void sendProfileField(final String nickName, final String dateOfBirth, final String gender, final String token) {

        mView.showProgressBar();
        profileRepository.sendProfileFieldRepository(nickName, gender, dateOfBirth, token, new ApiResult<Profile>() {
            @Override
            public void onSuccess(Profile result) {

                updateUserEntity(nickName,dateOfBirth,gender);
                getProfileFieldFromServer(token);
                mView.hideProgressBar();
                mView.showMessage("تغییرات با موفقیت ذخیره شد");

            }

            @Override
            public void onError(String massage) {
                mView.showMessage("عملیات ناموفق، دوباره تلاش کنید");
            }
        });
    }

    @Override
    public User getUserInfo() {
        User user = yaraDatabase.selectDao().getUserRecord();
        return user;
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
