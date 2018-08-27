package com.example.daryacomputer.yaratube.ui.login;

import android.widget.EditText;

import com.example.daryacomputer.yaratube.data.model.Product;

import java.util.List;

public interface MainLoginContract {

    interface View{

        void showMassage(String message);
    }

    interface Presenter{

    }

    interface onChildButtonClickListener {

        void goToLoginOptionFragment();
        void goToMobileLoginFragment();
        void goToActivationLoginFragment();
    }
}
