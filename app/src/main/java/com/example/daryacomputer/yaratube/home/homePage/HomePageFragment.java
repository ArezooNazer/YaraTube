package com.example.daryacomputer.yaratube.home.homePage;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.daryacomputer.yaratube.R;
import com.example.daryacomputer.yaratube.TransferToFragment;
import com.example.daryacomputer.yaratube.data.model.HomeItem;
import com.example.daryacomputer.yaratube.data.model.Product;

import java.util.ArrayList;
import java.util.List;


public class HomePageFragment extends Fragment  implements HomeContract.View{

    private List<HomeItem> homeItemList = new ArrayList<>();
    private HomeListAdapter homeListAdapter;
    private HomeContract.Presenter mPresenter;
    private RecyclerView mRecyclerView;

    public HomePageFragment() {
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
        return inflater.inflate(R.layout.fragment_home_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter = new HomePresenter(this);
        mRecyclerView = view.findViewById(R.id.homePageRecycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        homeListAdapter = new HomeListAdapter(getContext());
        mRecyclerView.setAdapter(homeListAdapter);

        mPresenter.getHomeItems();

    }



    @Override
    public void showProductList(List<HomeItem> homeItems) {

        homeListAdapter.updateData(homeItems);

    }

    @Override
    public void ShowMassage(String massage) {
        Toast.makeText(getContext(),massage,Toast.LENGTH_LONG);
    }
}
