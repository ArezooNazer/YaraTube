package com.example.yaratech.yaratube.ui.productgrid;

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
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.yaratech.yaratube.MainActivity;
import com.example.yaratech.yaratube.R;
import com.example.yaratech.yaratube.data.source.PaginationScrollListener;
import com.example.yaratech.yaratube.util.TransferToFragment;
import com.example.yaratech.yaratube.data.model.Category;
import com.example.yaratech.yaratube.data.model.Product;

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
    private Button retryBut;

    public ProductGridFragment() {

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
        getActivity().setTitle(category.getTitle());

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_product_grid, container, false);

        progressBar = view.findViewById(R.id.ProductListProgressBar);
        retryBut = view.findViewById(R.id.retryButton);
        retryBut.setVisibility(View.GONE);
        retryBut.bringToFront(); // for on click works on android 4.3!!!!

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
    public void onDestroy() {
        super.onDestroy();
        getActivity().setTitle(R.string.app_name);
    }

    @Override
    public void showProductList(List<Product> productList) {
        if(productGridAdapter.getItemCount()== 0){
            productGridAdapter.firstDataLoad(productList);
        }else {
            isLoading = false;
//            productGridAdapter.updateItems(productList);//using diffUtil
            productGridAdapter.updateData(productList);//using notify
        }
    }

    @Override
    public void showRetryOption() {
        retryBut.setVisibility(View.VISIBLE);
        retryBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.getProductList(category, 0);
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
        goToProductDetailFragment.goToProductDetailFragment(product, category.getTitle());
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
