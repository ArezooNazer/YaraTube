package com.example.daryacomputer.yaratube;

import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.daryacomputer.yaratube.home.MainContainerFragment;
import com.example.daryacomputer.yaratube.home.categoryPage.CategoryFragment;
import com.example.daryacomputer.yaratube.home.homePage.HomePageFragment;

public class MainActivity extends AppCompatActivity implements TransferToFragment {
    FragmentManager fm = getSupportFragmentManager();
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mirror layout to right
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        drawerLayout = findViewById(R.id.homePage);
        NavigationView navigationView = findViewById(R.id.homeDrawerLayout);

        //set a user profile photo on drawer header
        View header = navigationView.getHeaderView(0);
        ImageView imageView = header.findViewById(R.id.drawerUserPhoto);
        Glide.with(this).load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQTWkfQwNEw7GXCwMekUtAZIkIl3qowafpYe2Icr-e9wF46V0O5").into(imageView);

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
