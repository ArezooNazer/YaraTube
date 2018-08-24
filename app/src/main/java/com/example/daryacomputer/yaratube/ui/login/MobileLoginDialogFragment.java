package com.example.daryacomputer.yaratube.ui.login;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.daryacomputer.yaratube.MainActivity;
import com.example.daryacomputer.yaratube.R;
import com.example.daryacomputer.yaratube.TransferToFragment;
import com.example.daryacomputer.yaratube.data.source.Constant;
import com.example.daryacomputer.yaratube.data.source.LoginRepository;

public class MobileLoginDialogFragment extends DialogFragment implements LoginContract.View {


    private TransferToFragment transferToFragment;
    private LoginContract.Presenter mPresenter;
    private EditText phoneNumberEditText;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof MainActivity) {
            transferToFragment = (TransferToFragment) context;
        }
    }

    public MobileLoginDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new LoginPresenter(this);
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

        View view = inflater.inflate(R.layout.dialog_fragment_mobile_login, container, false);

        phoneNumberEditText = view.findViewById(R.id.mobileNumberET);

        final String Device_id = Settings.Secure.getString(getContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);

        Button button = view.findViewById(R.id.sendMobileNumberBut);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mPresenter.sendPhoneNumber(phoneNumberEditText.getText().toString().trim(),
                        Device_id,
                        Constant.DEVICE_MODEL,
                        Constant.DEVICE_OS);

                getDialog().dismiss();
                transferToFragment.goToActivationLoginDialogFragment();


            }
        });



        return view;
    }


    @Override
    public void showMassage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }


}
