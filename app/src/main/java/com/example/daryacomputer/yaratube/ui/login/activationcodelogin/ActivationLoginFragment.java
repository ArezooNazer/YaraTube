package com.example.daryacomputer.yaratube.ui.login.activationcodelogin;

import android.app.Dialog;
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
import com.example.daryacomputer.yaratube.TransferToFragment;
import com.example.daryacomputer.yaratube.ui.login.MainLoginContract;
import com.example.daryacomputer.yaratube.ui.login.MainLoginPresenter;
import com.example.daryacomputer.yaratube.ui.login.MainLoginDialogFragment;

public class ActivationLoginFragment extends Fragment implements ActivationLoginContract.View {

    public static String ACTIVATION_LOGIN_DIALOG = ActivationLoginFragment.class.getName();
    private MainLoginContract.onChildButtonClickListener mListener;
    private TransferToFragment transferToFragment;
    private ActivationLoginContract.Presenter mPresenter;
    private EditText activationEditText;
    private Button sendBut, clearEditTextBut;
    private String mobileNumber, deviceId;

    private MainLoginDialogFragment mainLoginDialogFragment = new MainLoginDialogFragment();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof MainActivity) {
            transferToFragment = (TransferToFragment) context;
        }
    }

    public ActivationLoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListener = (MainLoginContract.onChildButtonClickListener) getParentFragment();
        mPresenter = new ActivationLoginPresenter(this);


        Bundle bundle = getArguments();
        if (bundle == null) return;
        mobileNumber = bundle.getString("mobileNumber");
        deviceId = bundle.getString("deviceId");



    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_activation_login, container, false);

        activationEditText = view.findViewById(R.id.activationCodeET);
        sendBut = view.findViewById(R.id.saveBut);
        clearEditTextBut = view.findViewById(R.id.clearBut);

        sendBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mPresenter.sendActivationCode(mobileNumber, deviceId,
                        activationEditText.getText().toString().trim(),
                        "Arezoo");

            }
        });

        clearEditTextBut.setOnClickListener(new View.OnClickListener() {
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


    public static ActivationLoginFragment newInstance(String mobileNumber, String deviceId) {

        Bundle arg = new Bundle();
        arg.putString("mobileNumber", mobileNumber);
        arg.putString("deviceId", deviceId);

        ActivationLoginFragment activationLoginDialogFragment = new ActivationLoginFragment();
        activationLoginDialogFragment.setArguments(arg);
        return activationLoginDialogFragment;

    }

    @Override
    public void activationCodIsValid() {
        ((DialogFragment) getParentFragment()).dismiss();
        transferToFragment.goToProfileFragment();
    }
}
