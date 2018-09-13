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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.yaratech.yaratube.MainActivity;
import com.example.yaratech.yaratube.R;
import com.example.yaratech.yaratube.util.TransferToFragment;
import com.example.yaratech.yaratube.data.model.Product;
import com.example.yaratech.yaratube.data.model.Store;
import com.example.yaratech.yaratube.ui.productgrid.ProductGridContract;


public class HomePageFragment extends Fragment implements HomeContract.View, ProductGridContract.OnProductListItemListener {

    final static String PRODUCT = "product";
    private HomeContract.Presenter mPresenter;
    private RecyclerView mRecyclerView;
    HomeAdapter homeAdapter;
    ProgressBar progressBar;
    TransferToFragment goToDetailFragment;
    private Product product;
    ProductGridContract.OnProductListItemListener onProductListItemListener;

    public void setProduct(Product product) {
        this.product = product;
    }

    public HomePageFragment() {
        // Required empty public constructor
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mRecyclerView = view.findViewById(R.id.homePageRecycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(homeAdapter);

        progressBar = view.findViewById(R.id.homeProgressBar);

        mPresenter.getHomeItems();

    }

    public void showHomeItemList(Store store) {
        homeAdapter.updateData(store);
    }

    @Override
    public void showMessage(String massage) {
        Toast.makeText(getContext(), massage, Toast.LENGTH_LONG).show();
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