package com.example.yaratech.yaratube.ui.abuteus;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.example.yaratech.yaratube.R;
import com.example.yaratech.yaratube.ui.home.moreitem.MoreItemFragment;

public class AboutUsFragment extends Fragment {

    public static final String  ABOUT_US_FRAGMENT = AboutUsFragment.class.getName();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.aboutUs);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about_us, container, false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().setTitle(R.string.app_name);
    }
}
