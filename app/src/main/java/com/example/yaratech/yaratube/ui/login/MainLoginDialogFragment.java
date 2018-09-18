package com.example.yaratech.yaratube.ui.login;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.example.yaratech.yaratube.R;
import com.example.yaratech.yaratube.ui.login.activationcodelogin.ActivationFragment;
import com.example.yaratech.yaratube.ui.login.loginoption.LoginOptionFragment;
import com.example.yaratech.yaratube.ui.login.phonenumberlogin.PhoneNumberFragment;

import static com.example.yaratech.yaratube.MainActivity.yaraDatabase;
import static com.example.yaratech.yaratube.ui.login.activationcodelogin.ActivationFragment.ACTIVATION_LOGIN_DIALOG;
import static com.example.yaratech.yaratube.ui.login.phonenumberlogin.PhoneNumberFragment.MOBILE_LOGIN_DIALOG;

public class MainLoginDialogFragment extends DialogFragment implements MainLoginContract.onChildButtonClickListener {

    private LoginOptionFragment loginOptionFragment;
    private PhoneNumberFragment mobileLoginDialogFragment;
    private ActivationFragment activationLoginDialogFragment;

    public MainLoginDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginOptionFragment = new LoginOptionFragment();
        mobileLoginDialogFragment = new PhoneNumberFragment();
        activationLoginDialogFragment = new ActivationFragment();

        if (yaraDatabase.selectDao().selectPhoneNumber() != null)
            goToActivationLoginFragment();
        else
            goToLoginOptionFragment();

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);

        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main_login_dialog, container, false);
        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.round_border_white);
        getDialog().setCanceledOnTouchOutside(false);
        return view;
    }

    @Override
    public void goToLoginOptionFragment() {

        getChildFragmentManager().beginTransaction()
                .replace(R.id.loginDialogContainer, loginOptionFragment).commit();

    }

    @Override
    public void goToMobileLoginFragment() {

        getChildFragmentManager().beginTransaction()
                .addToBackStack(MOBILE_LOGIN_DIALOG)
                .replace(R.id.loginDialogContainer, mobileLoginDialogFragment).commit();

    }

    @Override
    public void goToActivationLoginFragment() {

        getChildFragmentManager().beginTransaction()
                .addToBackStack(ACTIVATION_LOGIN_DIALOG)
                .replace(R.id.loginDialogContainer, activationLoginDialogFragment).commit();

    }

}
