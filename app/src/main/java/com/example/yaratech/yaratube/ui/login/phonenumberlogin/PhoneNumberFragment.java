package com.example.yaratech.yaratube.ui.login.phonenumberlogin;

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

import com.example.yaratech.yaratube.R;
import com.example.yaratech.yaratube.data.source.Constant;
import com.example.yaratech.yaratube.ui.login.MainLoginContract;

public class PhoneNumberFragment extends Fragment implements PhoneNumberContract.View {

    public static String MOBILE_LOGIN_DIALOG = PhoneNumberFragment.class.getName();
    private MainLoginContract.onChildButtonClickListener mListener;
    private PhoneNumberContract.Presenter mPresenter;
    private EditText phoneNumberEditText;
    String TAG = PhoneNumberFragment.class.getName();

    public PhoneNumberFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mListener = (MainLoginContract.onChildButtonClickListener) getParentFragment();
        mPresenter = new PhoneNumberPresenter(this);
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
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean editTextVerification(EditText editText) {


        if (TextUtils.isEmpty(editText.getText())) {
            showMessage("لطفا شماره موبایل خود را وارد کنید");

        } else if (!TextUtils.isDigitsOnly(editText.getText()) || editText.getText().length() != 11 ||
                !editText.getText().toString().startsWith("09")) {

            showMessage("شماره موبایل وارد شده معتبر نیست");
        } else
            return true;

        return false;
    }

    @Override
    public void smsRequestReceived() {
        mListener.goToActivationLoginFragment();

    }

}
