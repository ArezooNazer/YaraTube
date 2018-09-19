package com.example.yaratech.yaratube.ui.productdetail;

import com.example.yaratech.yaratube.util.BaseView;
import com.example.yaratech.yaratube.data.model.Product;

public interface ProductDetailContract {

    interface View extends BaseView{
        void showProductDetail(Product product);
        void enableExoPlayer();
        void disableExoPlayer();

    }

    interface Presenter {
        void getProductDetail(Product product);
    }

}
