package com.example.daryacomputer.yaratube.ui.login.phonenumberlogin;

import com.example.daryacomputer.yaratube.data.YaraDatabase;
import com.example.daryacomputer.yaratube.data.entity.User;
import com.example.daryacomputer.yaratube.data.model.Login;
import com.example.daryacomputer.yaratube.data.source.ApiResult;
import com.example.daryacomputer.yaratube.data.source.LoginRepository;

import java.util.ArrayList;
import java.util.List;

import static com.example.daryacomputer.yaratube.MainActivity.yaraDatabase;

public class PhoneNumberLoginPresenter implements PhoneNumberLoginContract.Presenter {

    private LoginRepository loginRepository;
    private PhoneNumberLoginContract.View mView;



    public PhoneNumberLoginPresenter(PhoneNumberLoginContract.View mView) {

        loginRepository = new LoginRepository();
        this.mView = mView;
    }


    @Override
    public void sendPhoneNumber(final String mobileNum, final String deviceId, final String deviceModel, final String deviceOs) {

        loginRepository.sendPhoneNumberRepository(mobileNum, deviceId, deviceModel, deviceOs, new ApiResult<Login>() {
            @Override
            public void onSuccess(Login result) {

                   mView.smsRequestReceived(mobileNum,deviceId);
            }

            @Override
            public void onError(String massage) {

                mView.showMassage("ارسال با موفقیت انجام نشد، ارتباط با اینترنت بر قرار نیست.");
            }
        });
    }




}
