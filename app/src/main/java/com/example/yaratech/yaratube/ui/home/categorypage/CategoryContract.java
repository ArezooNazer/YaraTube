package com.example.yaratech.yaratube.ui.home.categorypage;

import com.example.yaratech.yaratube.util.BaseView;
import com.example.yaratech.yaratube.data.model.Category;

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
