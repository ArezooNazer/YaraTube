package com.example.yaratech.yaratube.ui.profile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.yaratech.yaratube.MainActivity;
import com.example.yaratech.yaratube.R;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

import static android.widget.Toast.LENGTH_LONG;

public class ProfileFragment extends Fragment {

    public static String PROFILE_FRAGMENT = ProfileFragment.class.getName();

    public ProfileFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);


        Toolbar mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        if (mToolbar != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);

            ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_forward_black_24dp);
            actionBar.setTitle("پروفایل");
        }


        Button cancelBut = view.findViewById(R.id.cancelBut);
        cancelBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        return view;
    }



    public void onBackPressed() {

        FragmentManager fm = getActivity().getSupportFragmentManager();
        fm.popBackStack();
    }

}
