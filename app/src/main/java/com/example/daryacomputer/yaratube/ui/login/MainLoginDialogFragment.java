package com.example.daryacomputer.yaratube.ui.login;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.example.daryacomputer.yaratube.R;
import com.example.daryacomputer.yaratube.ui.login.activationcodelogin.ActivationLoginFragment;
import com.example.daryacomputer.yaratube.ui.login.phonenumberlogin.PhoneNumberLoginFragment;

import static com.example.daryacomputer.yaratube.ui.login.activationcodelogin.ActivationLoginFragment.ACTIVATION_LOGIN_DIALOG;
import static com.example.daryacomputer.yaratube.ui.login.phonenumberlogin.PhoneNumberLoginFragment.MOBILE_LOGIN_DIALOG;

public class MainLoginDialogFragment extends DialogFragment implements MainLoginContract.onChildButtonClickListener {

    private LoginOptionFragment loginOptionFragment;
    private PhoneNumberLoginFragment mobileLoginDialogFragment;
    private ActivationLoginFragment activationLoginDialogFragment;

    public MainLoginDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginOptionFragment = new LoginOptionFragment();
        mobileLoginDialogFragment = new PhoneNumberLoginFragment();
        goToLoginOptionFragment();

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);

        // request a window without the title
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_login_dialog, container, false);
    }

    @Override
    public void goToLoginOptionFragment() {

        getChildFragmentManager().beginTransaction()
                .replace(R.id.loginDialogContainer, loginOptionFragment).commit();

    }

    @Override
    public void goToMobileLoginFragment() {

        if (loginOptionFragment.isVisible()) {
            getChildFragmentManager().beginTransaction()
                    .hide(loginOptionFragment)
                    .addToBackStack(MOBILE_LOGIN_DIALOG)
                    .add(R.id.loginDialogContainer, mobileLoginDialogFragment).commit();

        } else if (activationLoginDialogFragment.isVisible()) {
            getChildFragmentManager().beginTransaction()
                    .hide(activationLoginDialogFragment)
                    .show(mobileLoginDialogFragment).commit();

        } else {
            getChildFragmentManager().beginTransaction()
                    .addToBackStack(MOBILE_LOGIN_DIALOG)
                    .add(R.id.loginDialogContainer, mobileLoginDialogFragment).commit();
        }


    }

    @Override
    public void goToActivationLoginFragment(String mobileNumber, String deviceId) {

        activationLoginDialogFragment = ActivationLoginFragment.newInstance(mobileNumber, deviceId);

        if (mobileLoginDialogFragment.isVisible()) {
            getChildFragmentManager().beginTransaction()
                    .hide(mobileLoginDialogFragment)
                    .addToBackStack(ACTIVATION_LOGIN_DIALOG)
                    .add(R.id.loginDialogContainer, activationLoginDialogFragment).commit();

        } else {
            getChildFragmentManager().beginTransaction()
                    .addToBackStack(ACTIVATION_LOGIN_DIALOG)
                    .add(R.id.loginDialogContainer, activationLoginDialogFragment).commit();
        }

    }

}
