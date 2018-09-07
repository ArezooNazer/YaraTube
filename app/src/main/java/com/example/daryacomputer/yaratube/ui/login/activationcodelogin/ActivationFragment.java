package com.example.daryacomputer.yaratube.ui.login.activationcodelogin;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.daryacomputer.yaratube.MainActivity;
import com.example.daryacomputer.yaratube.R;
import com.example.daryacomputer.yaratube.data.entity.User;
import com.example.daryacomputer.yaratube.ui.login.MainLoginContract;
import com.example.daryacomputer.yaratube.util.TransferToFragment;

import static com.example.daryacomputer.yaratube.MainActivity.yaraDatabase;

public class ActivationFragment extends Fragment implements ActivationContract.View {

    public static String ACTIVATION_LOGIN_DIALOG = ActivationFragment.class.getName();
    private MainLoginContract.onChildButtonClickListener mListener;
    private TransferToFragment transferToFragment;
    private ActivationContract.Presenter mPresenter;
    private EditText activationEditText;
    private Button sendActivationCodeBut, editPhoneNumberBut;
    private String mobileNumber, deviceId;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof MainActivity) {
            transferToFragment = (TransferToFragment) context;
        }
    }

    public ActivationFragment() {
        // Required empty public constructor
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


        SMSListener.bindListener(new ActivationContract.OTPListener() {
            @Override
            public void onOTPReceived(String extractedOTP) {
                activationEditText.setText(extractedOTP);
            }
        });


        sendActivationCodeBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mPresenter.sendActivationCode(mobileNumber, deviceId,
                        activationEditText.getText().toString().trim(),
                        "Arezoo");

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
    public void showMassage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }


    @Override
    public void activationCodIsValid() {
        ((DialogFragment) getParentFragment()).dismiss();
//        if (LOGIN_FROM_COMMENT)
//            transferToFragment.goToCommentDialogFragment();
//        else
        transferToFragment.goToProfileFragment();
    }
}
