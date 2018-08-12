package com.example.daryacomputer.yaratube.home;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.daryacomputer.yaratube.MainActivity;
import com.example.daryacomputer.yaratube.R;
import com.example.daryacomputer.yaratube.TransferToFragment;


public class MainContainerFragment extends Fragment  {

    private static final String TAG = MainContainerFragment.class.getName();
    DrawerLayout drawerLayout;
    private TransferToFragment transferToFragment;

    public MainContainerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof MainActivity){
            transferToFragment = (TransferToFragment) context;
        }else{
            throw new ClassCastException(context.toString()+" must implement OnMainActivityCallback!");
        }
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

        drawerLayout = homeView.findViewById(R.id.homePage);

        BottomNavigationView bottomNavigationView = homeView.findViewById(R.id.homeBottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.homeBottomItem:

                                transferToFragment.goHomePageFragment();
                                return true;

                            case R.id.categoryBottomItem:
                                transferToFragment.goToCategoryFragment();
                                return true;
                        }

                        return true;
                    }
                });

        return homeView;
    }


}

