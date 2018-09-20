package com.example.yaratech.yaratube.ui.home.categorypage;

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
import com.example.yaratech.yaratube.data.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment implements CategoryContract.View, CategoryContract.OnCategoryItemListener {

    public static final String  CATEGORY_FRAGMENT = CategoryFragment.class.getName();
    private List<Category> categoryList = new ArrayList<>();
    private TransferToFragment goToProductListFragment;
    private CategoryContract.Presenter mPresenter;
    private CategoryAdapter categoryAdapter;
    private ProgressBar progressBar;
    private Button retryBut;

    public CategoryFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof MainActivity) {
            goToProductListFragment = (TransferToFragment) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement OnMainActivityCallback!");
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categoryAdapter = new CategoryAdapter(categoryList, getContext(), this);
        mPresenter = new CategoryPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        retryBut = view.findViewById(R.id.retryButton);
        retryBut.setVisibility(View.GONE);
        retryBut.bringToFront(); // for on click works on android 4.3!!!!
        progressBar = view.findViewById(R.id.categoryProgressBar);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.categoryRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(categoryAdapter);

        mPresenter.getCategoryList();
    }

    @Override
    public void showCategoryList(List<Category> categoryList) {
        categoryAdapter.updateData(categoryList);
    }

    @Override
    public void showRetryOption() {
        retryBut.setVisibility(View.VISIBLE);
        retryBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.getCategoryList();
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
    public void onCategoryItemClick(Category category) {
        goToProductListFragment.goToProductGridFragment(category);
    }
}