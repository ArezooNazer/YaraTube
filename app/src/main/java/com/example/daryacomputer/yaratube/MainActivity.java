package com.example.daryacomputer.yaratube;

import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.daryacomputer.yaratube.home.MainContainerFragment;
import com.example.daryacomputer.yaratube.productlist.ProductListFragment;

public class MainActivity extends AppCompatActivity  implements TransferToFragment{

    final static String PRODUCT_LIST_FRAGMENT = ProductListFragment.class.getSimpleName();
    FragmentManager fragmentManager = getSupportFragmentManager();
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
        fragmentManager.beginTransaction().replace(R.id.mainContainer, mainContainerFragment).commit();
    }

    @Override
    public void goToProductListFragment(int catId) {

        ProductListFragment productListFragment = ProductListFragment.newInstance(catId);
        fragmentManager.beginTransaction()
                .addToBackStack(PRODUCT_LIST_FRAGMENT)
                .add(R.id.mainContainer , productListFragment).commit();

    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        fragmentManager.popBackStack();

    }
}
