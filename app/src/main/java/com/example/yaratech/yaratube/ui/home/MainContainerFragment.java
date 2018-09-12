package com.example.yaratech.yaratube.ui.home;


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

import com.example.yaratech.yaratube.R;
import com.example.yaratech.yaratube.ui.home.categoryPage.CategoryFragment;
import com.example.yaratech.yaratube.ui.home.homePage.HomePageFragment;


public class MainContainerFragment extends Fragment {

    private static final String TAG = MainContainerFragment.class.getName();
    private HomePageFragment homePageFragment;
    private CategoryFragment categoryFragment;
    private MoreItemFragment moreItemFragment;
    private Fragment activeFragment;

    public MainContainerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        homePageFragment = new HomePageFragment();
        categoryFragment = new CategoryFragment();
        moreItemFragment = new MoreItemFragment();

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

        getChildFragmentManager().beginTransaction()
                .add(R.id.homeContainer, homePageFragment)
                .add(R.id.homeContainer, categoryFragment)
                .add(R.id.homeContainer, moreItemFragment)
                .hide(categoryFragment)
                .hide(moreItemFragment)
                .show(homePageFragment)
                .commit();

        activeFragment = homePageFragment;

        onBottomNavigationListener(homeView);

        return homeView;
    }


    public void onBottomNavigationListener(View view) {

        BottomNavigationView bottomNavigationView = view.findViewById(R.id.homeBottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new MyOnNavigationItemSelectedListener());
    }


    private class MyOnNavigationItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.homeBottomItem:

                    goHomePageFragment();
                    return true;

                case R.id.categoryBottomItem:

                    goToCategoryFragment();
                    return true;

                case R.id.moreBottomItem:
                    goToMoreItemFragment();
                    return true;
            }
            return true;
        }
    }


    public void goHomePageFragment() {

        getChildFragmentManager().beginTransaction()
                .hide(activeFragment)
                .show(homePageFragment).commit();

        activeFragment = homePageFragment;
    }

    public void goToCategoryFragment() {

        getChildFragmentManager().beginTransaction()
                .hide(activeFragment)
                .show(categoryFragment).commit();

        activeFragment = categoryFragment;
    }

    public void goToMoreItemFragment(){

        getChildFragmentManager().beginTransaction()
                .hide(activeFragment)
                .show(moreItemFragment).commit();

        activeFragment = moreItemFragment;
    }


}

