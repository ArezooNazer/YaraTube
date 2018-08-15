package com.example.daryacomputer.yaratube.home.categoryPage;

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

                mView.showProgressBar();
                mView.showCategoryList(result);
                mView.hideProgressBar();
            }

            @Override
            public void onError(String massage) {
                mView.ShowMessage(massage);
            }

        });

    }
}
