package com.example.yaratech.yaratube.ui.home.categorypage;

import com.example.yaratech.yaratube.data.model.Category;
import com.example.yaratech.yaratube.data.source.ApiResult;
import com.example.yaratech.yaratube.data.source.CategoryRepository;


import java.util.List;

public class CategoryPresenter implements CategoryContract.Presenter {

    private CategoryRepository categoryRepository;
    private CategoryContract.View mView;

    public CategoryPresenter(CategoryContract.View mView) {
        this.mView = mView;
        categoryRepository = new CategoryRepository();

    }

    @Override
    public void getCategoryList() {

        mView.hideRetryOption();
        mView.showProgressBar();

        categoryRepository.getCategoryList(new ApiResult<List<Category>>() {
            @Override
            public void onSuccess(List<Category> result) {
                mView.showCategoryList(result);
                mView.hideProgressBar();
            }

            @Override
            public void onError(String message) {
                mView.hideProgressBar();
                mView.showMessage(message);
                mView.showRetryOption();
            }

        });

    }
}
