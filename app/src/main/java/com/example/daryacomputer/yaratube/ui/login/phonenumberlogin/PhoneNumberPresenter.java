package com.example.daryacomputer.yaratube.ui.login.phonenumberlogin;

import android.util.Log;

import com.example.daryacomputer.yaratube.data.entity.User;
import com.example.daryacomputer.yaratube.data.model.Login;
import com.example.daryacomputer.yaratube.data.source.ApiResult;
import com.example.daryacomputer.yaratube.data.source.LoginRepository;

import static com.example.daryacomputer.yaratube.MainActivity.yaraDatabase;

public class PhoneNumberPresenter implements PhoneNumberContract.Presenter {

    private LoginRepository loginRepository;
    private PhoneNumberContract.View mView;



    public PhoneNumberPresenter(PhoneNumberContract.View mView) {

        loginRepository = new LoginRepository();
        this.mView = mView;
    }


    @Override
    public void sendPhoneNumber(final String mobileNum, final String deviceId, final String deviceModel, final String deviceOs) {

        loginRepository.sendPhoneNumberRepository(mobileNum, deviceId, deviceModel, deviceOs, new ApiResult<Login>() {
            @Override
            public void onSuccess(Login result) {

                    User user = new User();
                    user.setPhoneNumber(mobileNum);
                    user.setDeviceId(deviceId);
                    user.setDeviceModel(deviceModel);
                    user.setDeviceOs(deviceOs);
                    yaraDatabase.insertDao().saveUserInfo(user);

                    Log.d("id", String.valueOf(yaraDatabase.selectDao().getUserRecord().getId()));

                    mView.smsRequestReceived();
            }

            @Override
            public void onError(String massage) {

                mView.showMassage("ارسال با موفقیت انجام نشد، دوباره تلاش کنید");
            }
        });
    }




}
