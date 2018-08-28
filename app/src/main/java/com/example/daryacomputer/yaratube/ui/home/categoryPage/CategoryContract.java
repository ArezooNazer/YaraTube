package com.example.daryacomputer.yaratube.ui.home.categoryPage;

import com.example.daryacomputer.yaratube.BaseView;
import com.example.daryacomputer.yaratube.data.model.Category;

import java.util.List;

public interface CategoryContract {

    interface View extends BaseView {
        void showCategoryList(List<Category> categoryList);
    }

    interface Presenter {
        void getCategoryList();
    }

    interface OnCategoryItemListener {

        void onCategoryItemClick(Category category);
    }

}
