package com.example.daryacomputer.yaratube.ui.productgrid;

import com.example.daryacomputer.yaratube.util.BaseView;
import com.example.daryacomputer.yaratube.data.model.Category;
import com.example.daryacomputer.yaratube.data.model.Product;

import java.util.List;

public interface ProductGridContract {

    interface View extends BaseView {
        void showProductList(List<Product> productList);
    }

    interface Presenter {
        void getProductList(Category category, int offset);
    }

    interface OnProductListItemListener {

        void onProductListItemClick(Product product);
    }
}
