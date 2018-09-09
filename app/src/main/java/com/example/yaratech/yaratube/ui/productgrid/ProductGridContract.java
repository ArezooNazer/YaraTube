package com.example.yaratech.yaratube.ui.productgrid;

import com.example.yaratech.yaratube.util.BaseView;
import com.example.yaratech.yaratube.data.model.Category;
import com.example.yaratech.yaratube.data.model.Product;

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
