package com.example.daryacomputer.yaratube;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.daryacomputer.yaratube.home.HomeFragment;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mirror layout to right
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        HomeFragment homeFragment = new HomeFragment();
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.mainContainer,homeFragment).commit();
    }

}
