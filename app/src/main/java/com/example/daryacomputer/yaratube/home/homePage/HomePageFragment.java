package com.example.daryacomputer.yaratube.home.homePage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.daryacomputer.yaratube.R;
import com.example.daryacomputer.yaratube.data.model.Homeitem;

import java.util.ArrayList;
import java.util.List;


public class HomePageFragment extends Fragment  implements HomeContract.View{

    private List<Homeitem> homeItemList = new ArrayList<>();
    private HomeListAdapter homeListAdapter;
    private HomeContract.Presenter mPresenter;
    private RecyclerView mRecyclerView;

    public HomePageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        homeListAdapter = new HomeListAdapter(homeItemList, getContext());
        mPresenter = new HomePresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mRecyclerView = view.findViewById(R.id.homePageRecycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(homeListAdapter);

        mPresenter.getHomeItems();

    }

    public void showHomeItemList(List<Homeitem> homeItems) {
        homeListAdapter.updateData(homeItems);
    }

    @Override
    public void ShowMassage(String massage) {
        Toast.makeText(getContext(),massage,Toast.LENGTH_LONG);
    }
}
