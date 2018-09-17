package com.example.yaratech.yaratube.ui.profile;

import android.util.Log;

import com.example.yaratech.yaratube.data.entity.User;
import com.example.yaratech.yaratube.data.model.Profile;
import com.example.yaratech.yaratube.data.model.ProfileGetResponse;
import com.example.yaratech.yaratube.data.source.ApiResult;
import com.example.yaratech.yaratube.data.source.ProfileRepository;

import okhttp3.MultipartBody;

import static com.example.yaratech.yaratube.MainActivity.yaraDatabase;

public class ProfilePresenter implements ProfileContract.Presenter {

    public static String ProfilePresenter = ProfilePresenter.class.getName();
    private ProfileRepository profileRepository;
    private ProfileContract.View mView;
//    private String resultNickname, resultGender, resultBirthDate;

    public ProfilePresenter(ProfileContract.View mView) {
        this.mView = mView;
        profileRepository = new ProfileRepository();
    }

    @Override
    public void getProfileFiledFromDb() {
        mView.showProgressBar();
        User user = yaraDatabase.selectDao().getUserRecord();
        if (user != null)
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

                Log.d("ProfilePresenter", "nickname : " + result.getData().getNickname() + " gender : " + result.getData().getGender() + " dateOfBirth : " + result.getData().getDateOfBirth());
                //if user registered before, user data is still on sever

//                if(result.getData().getNickname() != null)
//                    resultNickname = result.getData().getNickname();
//                else
//                    resultNickname = nickName;
//
//                if( result.getData().getGender() != null)
//                    resultGender = (String) result.getData().getGender();
//                else
//                    resultGender = gender;
//
//                if ( result.getData().getDateOfBirth() != null)
//                    resultBirthDate = (String) result.getData().getDateOfBirth();
//                else
//                    resultBirthDate = dateOfBirth;

                updateUserEntity(nickName, dateOfBirth, gender);
//                getProfileFieldFromServer(token);
                getProfileFiledFromDb();
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
    public void sendUserAvatarToServer(MultipartBody.Part body, String token) {

        mView.showProgressBar();
        profileRepository.uploadUserAvatar(body, token, new ApiResult<Profile>() {
            @Override
            public void onSuccess(Profile result) {

                updateUserEntity(result.getData().getAvatar());
                getProfileFiledFromDb();
                mView.hideProgressBar();
                Log.d("TAG", "onSuccess() called with: result = [" + result.getError() + "]");
            }

            @Override
            public void onError(String massage) {
                mView.showMessage("حجم فایل باید کمتر از 1m و نوع فایل jpg باشد!!!!");
            }
        });
    }

    @Override
    public User getUserInfo() {
        User user = yaraDatabase.selectDao().getUserRecord();
        return user;
    }

    private void updateUserEntity(String nickName, String dateOfBirth, String gender) {

        User user = yaraDatabase.selectDao().getUserRecord();
        user.setName(nickName);
        user.setNickname(nickName);
        user.setGender(gender);
        user.setBirthDate(dateOfBirth);
        yaraDatabase.insertDao().updateUserInfo(user);

        Log.d("ProfilePresenter", "nickname : " + user.getName() + " gender : " + user.getGender() + " dateOfBirth : " + user.getBirthDate());
    }

    private void updateUserEntity(Object avatarUrl) {

        User user = yaraDatabase.selectDao().getUserRecord();
        user.setImage(String.valueOf(avatarUrl));
        yaraDatabase.insertDao().updateUserInfo(user);

        Log.d("ProfilePresenter", "avatarUrl : " + avatarUrl + " user.getImage() : " + user.getImage());
    }

}