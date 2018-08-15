package com.example.daryacomputer.yaratube.home;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.daryacomputer.yaratube.MainActivity;
import com.example.daryacomputer.yaratube.R;
import com.example.daryacomputer.yaratube.TransferToFragment;
import com.example.daryacomputer.yaratube.home.categoryPage.CategoryFragment;
import com.example.daryacomputer.yaratube.home.homePage.HomePageFragment;


public class MainContainerFragment extends Fragment implements TransferToFragment{

    private static final String TAG = MainContainerFragment.class.getName();
    private TransferToFragment transferToFragment;

    public MainContainerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View homeView = inflater.inflate(R.layout.fragment_main_container, container, false);

        Toolbar mToolbar = (Toolbar) homeView.findViewById(R.id.toolbar);
        if (mToolbar != null) {
            ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);

            ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            actionBar.setTitle("یارا تیوب");
        }

        goHomePageFragment();
        onBottomNavigationListener(homeView);

        return homeView;
    }


    public void onBottomNavigationListener(View view){

        BottomNavigationView bottomNavigationView = view.findViewById(R.id.homeBottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.homeBottomItem:

                                goHomePageFragment();
                                return true;

                            case R.id.categoryBottomItem:
                                goToCategoryFragment();
                                return true;
                        }

                        return true;
                    }
                });
    }

    @Override
    public void goHomePageFragment() {

        HomePageFragment homePageFragment = new HomePageFragment();
        getChildFragmentManager().beginTransaction()
                .replace(R.id.homeContainer, homePageFragment).commit();
    }

    @Override
    public void goToCategoryFragment() {
        CategoryFragment categoryFragment = new CategoryFragment();
        getChildFragmentManager().beginTransaction()
                .replace(R.id.homeContainer, categoryFragment).commit();
    }

}

