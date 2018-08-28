package com.example.daryacomputer.yaratube.ui.productdetail;

import com.example.daryacomputer.yaratube.BaseView;
import com.example.daryacomputer.yaratube.data.model.Product;

public interface ProductDetailContract {

    interface View extends BaseView{
        void showProductDetail(Product product);

    }

    interface Presenter {
        void getProductDetail(Product product);
    }

}
