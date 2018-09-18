package com.example.yaratech.yaratube.ui.login.activationcodelogin;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yaratech.yaratube.MainActivity;
import com.example.yaratech.yaratube.R;
import com.example.yaratech.yaratube.data.entity.User;
import com.example.yaratech.yaratube.ui.login.MainLoginContract;
import com.example.yaratech.yaratube.util.TransferToFragment;

import static com.example.yaratech.yaratube.MainActivity.yaraDatabase;

public class ActivationFragment extends Fragment implements ActivationContract.View {

    public static String ACTIVATION_LOGIN_DIALOG = ActivationFragment.class.getName();
    private MainLoginContract.onChildButtonClickListener mListener;
    private TransferToFragment transferToFragment;
    private ActivationContract.Presenter mPresenter;
    private EditText activationEditText;
    private Button sendActivationCodeBut, editPhoneNumberBut;
    private String mobileNumber, deviceId;
    //define a value to handle the permission callback, in onRequestPermissionsResult():
    public final static int REQUEST_CODE_READ_SMS = 1;

    public ActivationFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof MainActivity) {
            transferToFragment = (TransferToFragment) context;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mListener = (MainLoginContract.onChildButtonClickListener) getParentFragment();
        mPresenter = new ActivationPresenter(this);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_activation_login, container, false);

        User user = yaraDatabase.selectDao().getUserRecord();
        mobileNumber = user.getPhoneNumber();
        deviceId = user.getDeviceId();

        activationEditText = view.findViewById(R.id.activationCodeET);
        sendActivationCodeBut = view.findViewById(R.id.sendActivationCodeBut);
        editPhoneNumberBut = view.findViewById(R.id.editPhoneNumberBut);

        ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.RECEIVE_SMS},REQUEST_CODE_READ_SMS);

        SMSListener.bindListener(new ActivationContract.OTPListener() {
            @Override
            public void onOTPReceived(String extractedOTP) {
                activationEditText.setText(extractedOTP);
            }
        });


        sendActivationCodeBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                String nickname = stringGenerator();
                String nickname ="";
                mPresenter.sendActivationCode(mobileNumber, deviceId,
                        activationEditText.getText().toString().trim(), nickname);

                SMSListener.unbindListener();

            }
        });

        editPhoneNumberBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mListener.goToMobileLoginFragment();
            }
        });

        return view;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }


    @Override
    public void activationCodIsValid() {
        ((DialogFragment) getParentFragment()).dismiss();
        transferToFragment.goToProfileFragment();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_CODE_READ_SMS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getContext(), "Permission Granted!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Permission Denied!", Toast.LENGTH_SHORT).show();
                }
        }
    }
}
