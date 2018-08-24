package com.example.daryacomputer.yaratube.ui.login;

import com.example.daryacomputer.yaratube.data.model.Product;

import java.util.List;

public interface LoginContract {

    interface View{

        void showMassage(String message);
    }

    interface Presenter{

        boolean sendPhoneNumber(String mobileNum,
                             String deviceId,
                             String deviceModel,
                             String deviceOs);
    }
}
