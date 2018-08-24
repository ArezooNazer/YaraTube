package com.example.daryacomputer.yaratube.ui.login;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
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

public class ActivationLoginDialogFragment extends DialogFragment implements LoginContract.View {

    private TransferToFragment transferToFragment;
    private LoginContract.Presenter mPresenter;
    private EditText activationEditText;
    private Button sendBut, clearEditTextBut;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof MainActivity) {
            transferToFragment = (TransferToFragment) context;
        }
    }

    public ActivationLoginDialogFragment() {
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

        View view = inflater.inflate(R.layout.dialog_fragment_activation_login, container, false);

        activationEditText = view.findViewById(R.id.activationCodeET);
        sendBut = view.findViewById(R.id.saveBut);
        clearEditTextBut = view.findViewById(R.id.clearBut);

        sendBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.sendActivationCode("09351075298",
                        "6dde1c7f50762d7",
                        activationEditText.getText().toString().trim(),
                        "Arezoo");

                getDialog().dismiss();
                transferToFragment.goToProfileFragment();
            }
        });

        clearEditTextBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activationEditText.getText().clear();
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
}
