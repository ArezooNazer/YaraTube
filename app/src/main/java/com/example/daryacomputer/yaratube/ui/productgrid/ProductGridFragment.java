package com.example.daryacomputer.yaratube.ui.productgrid;

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
import com.example.daryacomputer.yaratube.data.source.PaginationScrollListener;
import com.example.daryacomputer.yaratube.util.TransferToFragment;
import com.example.daryacomputer.yaratube.data.model.Category;
import com.example.daryacomputer.yaratube.data.model.Product;

import java.util.ArrayList;
import java.util.List;


public class ProductGridFragment extends Fragment implements ProductGridContract.View, ProductGridContract.OnProductListItemListener {

    public final static String PRODUCT_LIST_FRAGMENT = ProductGridFragment.class.getSimpleName();
    final static String CATEGORY = "categoryId";

    private static final int PAGE_START = 0;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES =3;
    private int currentPage = PAGE_START;

    private List<Product> productList = new ArrayList<>();
    private ProductGridContract.Presenter mPresenter;
    private ProductGridAdapter productGridAdapter;
    private GridLayoutManager gridLayoutManager;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private Category category;
    private TransferToFragment goToProductDetailFragment;

    public ProductGridFragment() {
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

        productGridAdapter = new ProductGridAdapter(productList, getContext(), this);
        gridLayoutManager = new GridLayoutManager(getContext(), 2);
        mPresenter = new ProductGridPresenter(this);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_grid, container, false);

        progressBar = view.findViewById(R.id.ProductListProgressBar);

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

        recyclerView = view.findViewById(R.id.ProductListRecyclerView);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(productGridAdapter);

        recyclerView.addOnScrollListener(new PaginationScrollListener(gridLayoutManager) {
            @Override
            protected void loadMoreItems() {

                isLoading = true;
                currentPage += 1; //Increment page index to load the next one
                mPresenter.getProductList(category, productGridAdapter.getItemCount());

            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }

        });
        mPresenter.getProductList(category, 0);

    }

    @Override
    public void showProductList(List<Product> productList) {
        if(productGridAdapter.getItemCount()== 0){
            productGridAdapter.firstDataLoad(productList);
        }else {
            isLoading = false;
//            productGridAdapter.updateItems(productList);
            productGridAdapter.updateData(productList);
        }
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

    @Override
    public void onProductListItemClick(Product product) {

        goToProductDetailFragment.goToProductDetailFragment(product);
    }

    //local methods

    public static ProductGridFragment newInstance(Category category) {
        Bundle arg = new Bundle();
        arg.putParcelable(CATEGORY, category);
        ProductGridFragment productListFragment = new ProductGridFragment();
        productListFragment.setArguments(arg);
        return productListFragment;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


}
