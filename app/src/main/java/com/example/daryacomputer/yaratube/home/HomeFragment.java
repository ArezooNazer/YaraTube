package com.example.daryacomputer.yaratube.home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.daryacomputer.yaratube.R;


public class HomeFragment extends Fragment {

    private static final String TAG = HomeFragment.class.getName();
    DrawerLayout drawerLayout;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View homeView = inflater.inflate(R.layout.fragment_home, container, false);

        drawerLayout = homeView.findViewById(R.id.homeContainer);
        BottomNavigationView bottomNavigationView = homeView.findViewById(R.id.homeBottomNavigation);


        return homeView;
    }


}

