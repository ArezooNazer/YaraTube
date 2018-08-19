package com.example.daryacomputer.yaratube.ui.productdetail;

import com.example.daryacomputer.yaratube.data.model.Product;

public interface ProductDetailContract {

    interface View {

        void showProductDetail(Product product);
//        void ShowMassage(String message);
//        void showProgressBar();
//        void hideProgressBar();
    }

    interface Presenter {

        void getProductDetail(Product product);
    }

}
