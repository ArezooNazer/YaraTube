package com.example.daryacomputer.yaratube.ui.productlist;

import com.example.daryacomputer.yaratube.data.model.Category;
import com.example.daryacomputer.yaratube.data.model.Product;
import com.example.daryacomputer.yaratube.data.source.ApiResult;
import com.example.daryacomputer.yaratube.data.source.ProductListRepository;

import java.util.List;

public class ProductListPresenter implements ProductListContract.Presenter{

    private ProductListRepository productListRepository;
    private ProductListContract.View mView;

    public ProductListPresenter(ProductListContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void getProductList(Category category) {
        productListRepository = new ProductListRepository();

        mView.showProgressBar();
        productListRepository.getProductList(category, new ApiResult<List<Product>>() {
            @Override
            public void onSuccess(List<Product> result) {

                mView.showProductList(result);
                mView.hideProgressBar();
            }

            @Override
            public void onError(String massage) {
                mView.hideProgressBar();
                mView.ShowMassage(massage);
            }
        });

    }
}
