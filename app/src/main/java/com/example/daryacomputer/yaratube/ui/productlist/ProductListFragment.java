package com.example.daryacomputer.yaratube.ui.productlist;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.daryacomputer.yaratube.MainActivity;
import com.example.daryacomputer.yaratube.R;
import com.example.daryacomputer.yaratube.TransferToFragment;
import com.example.daryacomputer.yaratube.data.model.Category;
import com.example.daryacomputer.yaratube.data.model.Product;

import java.util.ArrayList;
import java.util.List;


public class ProductListFragment extends Fragment implements ProductListContract.View, ProductListContract.OnProductListItemListener {

    public final static String PRODUCT_LIST_FRAGMENT = ProductListFragment.class.getSimpleName();
    final static String CATEGORY = "categoryId";
    private List<Product> productList = new ArrayList<>();
    private ProductListContract.Presenter mPresenter;
    private ProductListAdapter productListAdapter;
    private ProgressBar progressBar;
    private Category category;
    private TransferToFragment goToProductDetailFragment;

    public ProductListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof MainActivity) {
            goToProductDetailFragment = (TransferToFragment) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement OnMainActivityCallback!");
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle == null) return;
        setCategory((Category) bundle.getParcelable(CATEGORY));

        productListAdapter = new ProductListAdapter(productList, getContext(), this);
        mPresenter = new ProductListPresenter(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_list, container, false);

        Toolbar mToolbar = (Toolbar) view.findViewById(R.id.toolbar);

        if (mToolbar != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);

            ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            actionBar.setTitle(category.getTitle());
        }


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.ProductListRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(productListAdapter);

        progressBar = view.findViewById(R.id.ProductListProgressBar);

        mPresenter.getProductList(category);

    }

    @Override
    public void showProductList(List<Product> productList) {
        productListAdapter.updateData(productList);
    }

    @Override
    public void ShowMassage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgressBar() {

        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);

    }

    public static ProductListFragment newInstance(Category category) {
        Bundle arg = new Bundle();
        arg.putParcelable(CATEGORY, category);
        ProductListFragment productListFragment = new ProductListFragment();
        productListFragment.setArguments(arg);
        return productListFragment;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    @Override
    public void onProductListItemClick(Product product) {

        goToProductDetailFragment.goToProductDetailFragment(product);
    }
}
