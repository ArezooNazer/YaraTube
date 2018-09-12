package com.example.yaratech.yaratube.ui.login;

public interface MainLoginContract {

    interface View {
        void showMessage(String message);
    }

    interface Presenter{

    }

    interface onChildButtonClickListener {

        void goToLoginOptionFragment();
        void goToMobileLoginFragment();
        void goToActivationLoginFragment();
    }


}
