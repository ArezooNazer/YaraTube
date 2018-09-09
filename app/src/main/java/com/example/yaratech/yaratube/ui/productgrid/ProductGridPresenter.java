package com.example.yaratech.yaratube.ui.productgrid;

import com.example.yaratech.yaratube.data.model.Category;
import com.example.yaratech.yaratube.data.model.Product;
import com.example.yaratech.yaratube.data.source.ApiResult;
import com.example.yaratech.yaratube.data.source.ProductGridRepository;

import java.util.List;

public class ProductGridPresenter implements ProductGridContract.Presenter{

    private ProductGridRepository productListRepository;
    private ProductGridContract.View mView;

    public ProductGridPresenter(ProductGridContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void getProductList(Category category , int offset) {
        productListRepository = new ProductGridRepository();

        mView.showProgressBar();
        productListRepository.getProductList(category,offset, new ApiResult<List<Product>>() {
            @Override
            public void onSuccess(List<Product> result) {

                mView.hideProgressBar();
                mView.showProductList(result);
            }

            @Override
            public void onError(String massage) {
                mView.hideProgressBar();
                mView.ShowMassage(massage);
            }
        });

    }


}
