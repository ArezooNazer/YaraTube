package com.example.yaratech.yaratube.ui.home.homepage;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.yaratech.yaratube.MainActivity;
import com.example.yaratech.yaratube.R;
import com.example.yaratech.yaratube.util.TransferToFragment;
import com.example.yaratech.yaratube.data.model.Product;
import com.example.yaratech.yaratube.data.model.Store;
import com.example.yaratech.yaratube.ui.productgrid.ProductGridContract;


public class HomePageFragment extends Fragment implements HomeContract.View, ProductGridContract.OnProductListItemListener {

    public static final String  HOME_PAGE_FRAGMENT = HomePageFragment.class.getName();
    private TransferToFragment goToDetailFragment;
    private HomeContract.Presenter mPresenter;
    private RecyclerView mRecyclerView;
    private HomeAdapter homeAdapter;
    private ProgressBar progressBar;
    private Product product;
    private Button retryBut;

    public void setProduct(Product product) {
        this.product = product;
    }

    public HomePageFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof MainActivity) {
            goToDetailFragment = (TransferToFragment) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement OnMainActivityCallback!");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeAdapter = new HomeAdapter(getContext(), getChildFragmentManager(), this);
        mPresenter = new HomePresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);

        progressBar = view.findViewById(R.id.homeProgressBar);
        retryBut = view.findViewById(R.id.retryButton);
        retryBut.bringToFront(); // for on click works on android 4.3!!!!
        retryBut.setVisibility(View.GONE);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = view.findViewById(R.id.homePageRecycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(homeAdapter);

        mPresenter.getHomeItems();

    }

    public void showHomeItemList(Store store) {
        homeAdapter.updateData(store);
    }

    @Override
    public void showRetryOption() {
        retryBut.setVisibility(View.VISIBLE);
        retryBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.getHomeItems();
//                Toast.makeText(getContext(),"no intentet" ,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void hideRetryOption() {
        retryBut.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }


    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onProductListItemClick(Product product) {
        goToDetailFragment.goToProductDetailFragment(product);
    }

}