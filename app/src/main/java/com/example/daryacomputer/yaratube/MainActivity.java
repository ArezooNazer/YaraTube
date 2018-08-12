package com.example.daryacomputer.yaratube;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.daryacomputer.yaratube.home.MainContainerFragment;
import com.example.daryacomputer.yaratube.home.categoryPage.CategoryFragment;
import com.example.daryacomputer.yaratube.home.homePage.HomePageFragment;

public class MainActivity extends AppCompatActivity implements TransferToFragment {
    FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mirror layout to right
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        goToMainContainerFragment();

    }


    @Override
    public void goToMainContainerFragment() {
        MainContainerFragment mainContainerFragment = new MainContainerFragment();
        fm.beginTransaction().replace(R.id.mainContainer, mainContainerFragment).commit();
        goHomePageFragment();
    }

    @Override
    public void goHomePageFragment() {

        HomePageFragment homePageFragment = new HomePageFragment();
        fm.beginTransaction().replace(R.id.homeContainer, homePageFragment).commit();
    }

    @Override
    public void goToCategoryFragment() {
        CategoryFragment categoryFragment = new CategoryFragment();
        fm.beginTransaction().replace(R.id.homeContainer, categoryFragment).commit();
    }
}
