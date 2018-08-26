package com.example.daryacomputer.yaratube.ui.login.phonenumberlogin;

import com.example.daryacomputer.yaratube.data.model.Login;
import com.example.daryacomputer.yaratube.data.source.ApiResult;
import com.example.daryacomputer.yaratube.data.source.LoginRepository;

public class PhoneNumberLoginPresenter implements PhoneNumberLoginContract.Presenter {

    private LoginRepository loginRepository;
    private PhoneNumberLoginContract.View mView;



    public PhoneNumberLoginPresenter(PhoneNumberLoginContract.View mView) {
//        mListener = (MainLoginContract.onChildButtonClickListener) PhoneNumberLoginFragment.getParentFragment();
        loginRepository = new LoginRepository();
        this.mView = mView;
    }


    @Override
    public void sendPhoneNumber(final String mobileNum,final String deviceId, String deviceModel, String deviceOs) {

        loginRepository.sendPhoneNumberRepository(mobileNum, deviceId, deviceModel, deviceOs, new ApiResult<Login>() {
            @Override
            public void onSuccess(Login result) {
               mView.smsRequestReceived(mobileNum,deviceId);
            }

            @Override
            public void onError(String massage) {
                mView.showMassage("on Error");
            }
        });
    }




}
