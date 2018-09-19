package com.example.yaratech.yaratube.ui.productdetail;

import com.example.yaratech.yaratube.data.model.Product;
import com.example.yaratech.yaratube.data.source.ApiResult;
import com.example.yaratech.yaratube.data.source.ProductDetailRepository;

public class ProductDetailPresenter implements ProductDetailContract.Presenter {

    private ProductDetailRepository productDetailRepository;
    private ProductDetailContract.View mView;

    public ProductDetailPresenter(ProductDetailContract.View mView) {
        this.mView = mView;
        productDetailRepository = new ProductDetailRepository();
    }

    @Override
    public void getProductDetail(Product product) {

        mView.showProgressBar();
        productDetailRepository.getProductDetail(product, new ApiResult<Product>() {

            @Override
            public void onSuccess(Product result) {
                mView.enableExoPlayer();
                mView.showProductDetail(result);
                mView.hideProgressBar();
            }

            @Override
            public void onError(String message) {
                mView.disableExoPlayer();
                mView.hideProgressBar();
                mView.showMessage(message);
            }
        });
    }
}
