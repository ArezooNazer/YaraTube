package com.example.daryacomputer.yaratube.home.categoryPage;

import android.widget.ProgressBar;

import com.example.daryacomputer.yaratube.data.model.Category;

import java.util.List;

public interface CategoryContract {

    interface View{
        void showCategoryList(List<Category> categoryList);
        void ShowMessage(String message);

        void showProgressBar();
        void hideProgressBar();
    }

    interface Presenter{
        void getCategoryList();
    }


}
