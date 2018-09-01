package com.example.daryacomputer.yaratube;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.daryacomputer.yaratube.data.YaraDatabase;
import com.example.daryacomputer.yaratube.data.model.Category;
import com.example.daryacomputer.yaratube.data.model.Product;
import com.example.daryacomputer.yaratube.data.source.LoginRepository;
import com.example.daryacomputer.yaratube.ui.home.MainContainerFragment;
import com.example.daryacomputer.yaratube.ui.login.MainLoginContract;
import com.example.daryacomputer.yaratube.ui.login.MainLoginDialogFragment;
import com.example.daryacomputer.yaratube.ui.productdetail.ProductDetailFragment;
import com.example.daryacomputer.yaratube.ui.productdetail.comment.CommentDialogFragment;
import com.example.daryacomputer.yaratube.ui.productgrid.ProductGridFragment;
import com.example.daryacomputer.yaratube.ui.profile.ProfileFragment;
import com.example.daryacomputer.yaratube.util.TransferToFragment;

import static com.example.daryacomputer.yaratube.ui.productdetail.ProductDetailFragment.PRODUCT_DETAIL_FRAGMENT;
import static com.example.daryacomputer.yaratube.ui.productgrid.ProductGridFragment.PRODUCT_LIST_FRAGMENT;
import static com.example.daryacomputer.yaratube.ui.profile.ProfileFragment.PROFILE_FRAGMENT;

public class MainActivity extends AppCompatActivity implements TransferToFragment {


    private static final String DATABASE_NAME = "yara_db";
    private MainLoginContract.onChildButtonClickListener mListener;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private ProfileFragment profileFragment = new ProfileFragment();
    public  static YaraDatabase yaraDatabase;
    private DrawerLayout drawerLayout;


    private MainLoginDialogFragment mainLoginDialogFragment = new MainLoginDialogFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.homePage);
        NavigationView navigationView = findViewById(R.id.homeDrawerLayout);

        yaraDatabase = Room.databaseBuilder(getApplicationContext(), YaraDatabase.class, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                item.setChecked(true);
                drawerLayout.closeDrawers();

                switch (item.getItemId()) {

                    case R.id.drawerProfile:
                        if (LoginRepository.isLogin())
                            goToProfileFragment();
                        else
                            goToMainLoginDialogFragment();

                }
                return false;
            }
        });


        //set a user profile photo on drawer header
        View header = navigationView.getHeaderView(0);
        ImageView imageView = header.findViewById(R.id.drawerUserPhoto);
        Glide.with(this).load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQTWkfQwNEw7GXCwMekUtAZIkIl3qowafpYe2Icr-e9wF46V0O5").into(imageView);

        goToMainContainerFragment();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(Gravity.RIGHT);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void goToMainContainerFragment() {

        MainContainerFragment mainContainerFragment = new MainContainerFragment();
        fragmentManager.beginTransaction().replace(R.id.mainContainer, mainContainerFragment).commit();
    }

    @Override
    public void goToProductGridFragment(Category category) {

        ProductGridFragment productListFragment = ProductGridFragment.newInstance(category);
        fragmentManager.beginTransaction()
                .addToBackStack(PRODUCT_LIST_FRAGMENT)
                .add(R.id.mainContainer, productListFragment).commit();

    }

    @Override
    public void goToProductDetailFragment(Product product) {

        ProductDetailFragment productDetailFragment = ProductDetailFragment.newInstance(product);
        fragmentManager.beginTransaction()
                .addToBackStack(PRODUCT_DETAIL_FRAGMENT)
                .add(R.id.mainContainer, productDetailFragment).commit();
    }

    @Override
    public void goToProfileFragment() {

        if (!profileFragment.isAdded()) {

            fragmentManager.beginTransaction()
                    .addToBackStack(PROFILE_FRAGMENT)
                    .add(R.id.mainContainer, profileFragment).commit();
        } else if (profileFragment.isAdded() && profileFragment.isVisible()) {

            fragmentManager.beginTransaction()
                    .show(profileFragment);

        }

    }

    @Override
    public void goToMainLoginDialogFragment() {

        MainLoginDialogFragment mainLoginDialogFragment = new MainLoginDialogFragment();
        mainLoginDialogFragment.setCancelable(false);
        mainLoginDialogFragment.show(getSupportFragmentManager(), "LoginDialog");
    }

    @Override
    public void goToCommentDialogFragment(int productId) {

        CommentDialogFragment commentDialogFragment = CommentDialogFragment.newInstance(productId);
        commentDialogFragment.setCancelable(false);
        commentDialogFragment.show(getSupportFragmentManager(),"commentDialog");
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }

        super.onBackPressed();

    }



}
