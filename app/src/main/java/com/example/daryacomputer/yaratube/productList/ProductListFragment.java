package com.example.daryacomputer.yaratube.productList;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.daryacomputer.yaratube.R;
import com.example.daryacomputer.yaratube.data.model.Product;

import java.util.ArrayList;
import java.util.List;


public class ProductListFragment extends Fragment implements ProductListContract.View{

    private List<Product> productList = new ArrayList<>();
    private ProductListContract.Presenter mPresenter;
    private ProductListAdapter productListAdapter;
    private ProgressBar progressBar;
    int categoryId;

    public ProductListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        productListAdapter = new ProductListAdapter(productList , getContext());
        mPresenter = new ProductListPresenter(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.ProductListRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(productListAdapter);

        progressBar = view.findViewById(R.id.ProductListProgressBar);

        mPresenter.getProductList(categoryId);
    }

    @Override
    public void showProductList(List<Product> productList) {
        productListAdapter.updateData(productList);
    }

    @Override
    public void ShowMassage(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_LONG);
    }

    @Override
    public void showProgressBar() {

        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);

    }
}
