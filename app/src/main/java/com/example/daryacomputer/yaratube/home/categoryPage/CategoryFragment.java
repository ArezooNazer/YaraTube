package com.example.daryacomputer.yaratube.home.categoryPage;

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
import com.example.daryacomputer.yaratube.data.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment implements CategoryContract.View {

    private List<Category> categoryList = new ArrayList<>();
    private CategoryContract.Presenter mPresenter;
    private CategoryAdapter categoryAdapter;

    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        categoryAdapter = new CategoryAdapter(categoryList,getContext());
        mPresenter = new CategoryPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false);
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
    public void ShowMessage(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_LONG);
    }
}
