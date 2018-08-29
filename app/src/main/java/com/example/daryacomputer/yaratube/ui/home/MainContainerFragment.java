package com.example.daryacomputer.yaratube.ui.home;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.daryacomputer.yaratube.R;
import com.example.daryacomputer.yaratube.TransferToFragment;
import com.example.daryacomputer.yaratube.ui.home.categoryPage.CategoryFragment;
import com.example.daryacomputer.yaratube.ui.home.homePage.HomePageFragment;


public class MainContainerFragment extends Fragment {

    private static final String TAG = MainContainerFragment.class.getName();
    private HomePageFragment homePageFragment;
    private CategoryFragment categoryFragment;

    public MainContainerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        homePageFragment = new HomePageFragment();
        categoryFragment = new CategoryFragment();

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View homeView = inflater.inflate(R.layout.fragment_main_container, container, false);

        Toolbar mToolbar = (Toolbar) homeView.findViewById(R.id.toolbar);

        if (mToolbar != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);

            ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            actionBar.setTitle("یارا تیوب");
        }

        goHomePageFragment();
        onBottomNavigationListener(homeView);

        return homeView;
    }


    public void onBottomNavigationListener(View view) {

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


    public void goHomePageFragment() {

        if (homePageFragment.isVisible()) {

            getChildFragmentManager().beginTransaction()
                    .show(homePageFragment).commit();

        } else if (homePageFragment.isAdded() && homePageFragment.isHidden()) {

            getChildFragmentManager().beginTransaction()
                    .hide(categoryFragment)
                    .show(homePageFragment).commit();
        } else {

            getChildFragmentManager().beginTransaction()
                    .add(R.id.homeContainer, homePageFragment).commit();
        }
    }

    public void goToCategoryFragment() {

        if (categoryFragment.isVisible()) {

            getChildFragmentManager().beginTransaction()
                    .show(categoryFragment).commit();

        } else if (categoryFragment.isAdded() && categoryFragment.isHidden()) {

            getChildFragmentManager().beginTransaction()
                    .hide(homePageFragment)
                    .show(categoryFragment).commit();
        } else {

            getChildFragmentManager().beginTransaction()
                    .hide(homePageFragment)
                    .add(R.id.homeContainer, categoryFragment).commit();
        }

    }

}

