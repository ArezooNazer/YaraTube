package com.example.daryacomputer.yaratube.ui.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.daryacomputer.yaratube.R;


public class LoginOptionFragment extends Fragment {
    private MainLoginContract.onChildButtonClickListener mListener;
    private Button sendBut;


    public LoginOptionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mListener = (MainLoginContract.onChildButtonClickListener) getParentFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_option, container, false);

        sendBut = view.findViewById(R.id.loginViaPhoneNumber);
        sendBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mListener.goToMobileLoginFragment();

            }
        });

        return view;
    }

}
