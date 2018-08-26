package com.example.daryacomputer.yaratube.ui.login;

import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.daryacomputer.yaratube.R;
import com.example.daryacomputer.yaratube.data.source.Constant;

public class MobileLoginFragment extends Fragment implements LoginContract.View {

    public static String MOBILE_LOGIN_DIALOG = MobileLoginFragment.class.getName();
    private LoginContract.onChildButtonClickListener mListener;
    private LoginContract.Presenter mPresenter;
    private EditText phoneNumberEditText;


    public MobileLoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mListener = (LoginContract.onChildButtonClickListener) getParentFragment();
        mPresenter = new LoginPresenter(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_fragment_mobile_login, container, false);

        phoneNumberEditText = view.findViewById(R.id.mobileNumberET);

        final String Device_id = Settings.Secure.getString(getContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);

        Button button = view.findViewById(R.id.sendMobileNumberBut);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               boolean isLegal = editTextVerification(phoneNumberEditText);

               if(isLegal) {
                   mPresenter.sendPhoneNumber(phoneNumberEditText.getText().toString().trim(),
                           Device_id,
                           Constant.DEVICE_MODEL,
                           Constant.DEVICE_OS);

                  mListener.goToActivationLoginFragment(phoneNumberEditText.getText().toString().trim(),Device_id);
               }

            }
        });
        return view;
    }


    @Override
    public void showMassage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean editTextVerification(EditText editText) {


        if (TextUtils.isEmpty(editText.getText())) {
            showMassage("لطفا شماره موبایل خود را وارد کنید");

        } else if (!TextUtils.isDigitsOnly(editText.getText()) || editText.getText().length() != 11 ||
                !editText.getText().toString().startsWith("09")) {

            showMassage("شماره موبایل وارد شده معتبر نیست");
        } else
            return true;

        return false;
    }


}
