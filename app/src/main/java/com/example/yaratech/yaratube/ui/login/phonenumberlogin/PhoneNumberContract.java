package com.example.yaratech.yaratube.ui.login.phonenumberlogin;

import android.widget.EditText;

import com.example.yaratech.yaratube.ui.login.MainLoginContract;

public interface PhoneNumberContract {

    interface View extends MainLoginContract.View{
        boolean editTextVerification(EditText editText);
        void smsRequestReceived();
    }

    interface Presenter {
        void sendPhoneNumber(String mobileNum,
                             String deviceId,
                             String deviceModel,
                             String deviceOs);

    }
}
