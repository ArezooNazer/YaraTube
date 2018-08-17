package com.example.daryacomputer.yaratube.productlist;

import com.example.daryacomputer.yaratube.data.model.Product;

import java.util.List;

public interface ProductListContract {

    interface View{
        void showProductList(List<Product> productList);

        void ShowMassage(String message);

        void showProgressBar();
        void hideProgressBar();
    }

    interface Presenter{
        void getProductList(int categoryId);
    }

    interface OnCategoryItemListener{

        void onCategoryItemClick(int id);
    }
}
