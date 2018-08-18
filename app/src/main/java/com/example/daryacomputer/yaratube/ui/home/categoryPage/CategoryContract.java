package com.example.daryacomputer.yaratube.ui.home.categoryPage;

import com.example.daryacomputer.yaratube.data.model.Category;

import java.util.List;

public interface CategoryContract {

    interface View{
        void showCategoryList(List<Category> categoryList);
        void ShowMassage(String message);

        void showProgressBar();
        void hideProgressBar();
    }

    interface Presenter{
        void getCategoryList();
    }

    interface OnCategoryItemListener{

        void onCategoryItemClick(Category category);
    }

}