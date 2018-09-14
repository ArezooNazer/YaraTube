package com.example.yaratech.yaratube.ui.profile.pickavatar;

import android.util.Log;

import com.example.yaratech.yaratube.data.entity.User;
import com.example.yaratech.yaratube.data.model.Profile;
import com.example.yaratech.yaratube.data.source.ApiResult;
import com.example.yaratech.yaratube.data.source.UploadAvatarRepository;

import okhttp3.MultipartBody;

import static com.example.yaratech.yaratube.MainActivity.yaraDatabase;

public class PickAvatarPresenter implements PickAvatarContract.Presenter {

    private  String TAG = PickAvatarPresenter.class.getName();
    private PickAvatarContract.View mView;
    private UploadAvatarRepository uploadAvatarRepository;


    public PickAvatarPresenter(PickAvatarContract.View mView) {
        this.mView = mView;
        uploadAvatarRepository = new UploadAvatarRepository();
    }

    @Override
    public void sendUserAvatarToServer(MultipartBody.Part body, String token) {
        uploadAvatarRepository.uploadUserAvatar(body, token, new ApiResult<Profile>() {
            @Override
            public void onSuccess(Profile result) {

                updateUserEntity(result.getAvatar());
                mView.dismissDialog();
                Log.d("TAG", "onSuccess() called with: result = [" + result.getError() + "]");
            }

            @Override
            public void onError(String massage) {
//                mView.showMessage("خطا در بر قراری ارتباط با سرور");
            }
        });
    }


    private void updateUserEntity(Object avatarUrl){

        User user = yaraDatabase.selectDao().getUserRecord();
        user.setImage(String.valueOf(avatarUrl));
        yaraDatabase.insertDao().updateUserInfo(user);

        Log.d("ProfilePresenter", "avatarUrl : " + avatarUrl + " user.getImage() : " + user.getImage());
    }
}
