package com.example.daryacomputer.yaratube.ui.login;

import android.widget.EditText;

import com.example.daryacomputer.yaratube.data.model.Product;

import java.util.List;

public interface LoginContract {

    interface View{

        void showMassage(String message);
        boolean editTextVerification(EditText editText);
    }

    interface Presenter{

      void sendPhoneNumber(String mobileNum,
                             String deviceId,
                             String deviceModel,
                             String deviceOs);

      void sendActivationCode(String mobileNum,
                              String deviceId,
                              String verificationCode,
                              String nickName);
    }

    interface onChildButtonClickListener {

        void goToLoginOptionFragment();
        void goToMobileLoginFragment();
        void goToActivationLoginFragment(String mobileNumber, String deviceId);
    }
}
