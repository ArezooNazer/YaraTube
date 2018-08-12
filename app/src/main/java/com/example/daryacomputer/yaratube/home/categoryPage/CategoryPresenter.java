package com.example.daryacomputer.yaratube.home.categoryPage;

import android.widget.Toast;
import com.example.daryacomputer.yaratube.data.model.Category;
import com.example.daryacomputer.yaratube.data.source.ApiResult;
import com.example.daryacomputer.yaratube.data.source.CategoryRepository;


import java.util.List;

public class CategoryPresenter implements CategoryContract.Presenter {

    private CategoryRepository categoryRepository;
    private CategoryContract.View mView;

    public CategoryPresenter(CategoryContract.View mView) {
        this.mView = mView;

    }

    @Override
    public void getCategoryList() {

        categoryRepository = new CategoryRepository();
        categoryRepository.getCategoryList(new ApiResult<List<Category>>() {
            @Override
            public void onSuccess(List<Category> result) {
                mView.showCategoryList(result);
            }

            @Override
            public void onFailure() {
//               Toast.makeText( , "can not load category List", Toast.LENGTH_LONG);
            }
        });


    }
}