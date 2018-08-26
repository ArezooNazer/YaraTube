package com.example.daryacomputer.yaratube.ui.login.phonenumberlogin;

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
import com.example.daryacomputer.yaratube.ui.login.MainLoginContract;
import com.example.daryacomputer.yaratube.ui.login.MainLoginPresenter;

public class PhoneNumberLoginFragment extends Fragment implements PhoneNumberLoginContract.View {

    public static String MOBILE_LOGIN_DIALOG = PhoneNumberLoginFragment.class.getName();
    private MainLoginContract.onChildButtonClickListener mListener;
    private PhoneNumberLoginContract.Presenter mPresenter;
    private EditText phoneNumberEditText;


    public PhoneNumberLoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mListener = (MainLoginContract.onChildButtonClickListener) getParentFragment();
        mPresenter = new PhoneNumberLoginPresenter(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mobile_login, container, false);

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

    @Override
    public void smsRequestReceived(String mobileNum, String deviceId) {
        mListener.goToActivationLoginFragment(mobileNum,deviceId);

    }

}
