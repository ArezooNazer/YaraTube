package com.example.daryacomputer.yaratube.ui.login;

import android.content.Context;
import android.os.Bundle;
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

public class ActivationLoginFragment extends Fragment implements LoginContract.View {

    public static String ACTIVATION_LOGIN_DIALOG = ActivationLoginFragment.class.getName();
    private LoginContract.onChildButtonClickListener mListener;
    private TransferToFragment transferToFragment;
    private LoginContract.Presenter mPresenter;
    private EditText activationEditText;
    private Button sendBut, clearEditTextBut;
    private String mobileNumber, deviceId;

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
        mPresenter = new LoginPresenter(this);


        Bundle bundle = getArguments();
        if (bundle == null) return;
        mobileNumber = bundle.getString("mobileNumber");
        deviceId = bundle.getString("deviceId");

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_fragment_activation_login, container, false);

        activationEditText = view.findViewById(R.id.activationCodeET);
        sendBut = view.findViewById(R.id.saveBut);
        clearEditTextBut = view.findViewById(R.id.clearBut);

        sendBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mPresenter.sendActivationCode(mobileNumber, deviceId,
                        activationEditText.getText().toString().trim(),
                        "Arezoo");

                transferToFragment.goToProfileFragment();

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

    @Override
    public boolean editTextVerification(EditText editText) {
        return false;
    }

    public static ActivationLoginFragment newInstance(String mobileNumber, String deviceId) {

        Bundle arg = new Bundle();
        arg.putString("mobileNumber", mobileNumber);
        arg.putString("deviceId", deviceId);

        ActivationLoginFragment activationLoginDialogFragment = new ActivationLoginFragment();
        activationLoginDialogFragment.setArguments(arg);
        return activationLoginDialogFragment;

    }
}
