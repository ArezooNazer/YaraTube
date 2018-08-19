package com.example.daryacomputer.yaratube;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.daryacomputer.yaratube.data.model.Category;
import com.example.daryacomputer.yaratube.data.model.Product;
import com.example.daryacomputer.yaratube.ui.home.MainContainerFragment;
import com.example.daryacomputer.yaratube.ui.productdetail.ProductDetailFragment;
import com.example.daryacomputer.yaratube.ui.productlist.ProductListFragment;

import static com.example.daryacomputer.yaratube.ui.productdetail.ProductDetailFragment.PRODUCT_DETAIL_FRAGMENT;
import static com.example.daryacomputer.yaratube.ui.productlist.ProductListFragment.PRODUCT_LIST_FRAGMENT;

public class MainActivity extends AppCompatActivity  implements TransferToFragment {

    FragmentManager fragmentManager = getSupportFragmentManager();
    DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mirror layout to right
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

//        Toolbar toolbar = findViewById(R.id.mainToolbar);
//        setSupportActionBar(toolbar);
//
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
//        actionBar.setTitle("یارا تیوب");

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
    public void goToProductListFragment(Category category) {

        ProductListFragment productListFragment = ProductListFragment.newInstance(category);
        fragmentManager.beginTransaction()
                .addToBackStack(PRODUCT_LIST_FRAGMENT)
                .add(R.id.mainContainer , productListFragment).commit();

    }

    @Override
    public void goToProductDetailFragment(Product product) {

        ProductDetailFragment productDetailFragment = ProductDetailFragment.newInstance(product)    ;
        fragmentManager.beginTransaction()
                .addToBackStack(PRODUCT_DETAIL_FRAGMENT)
                .add(R.id.mainContainer, productDetailFragment).commit();
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }
}
