package com.example.yaratech.yaratube.ui.productdetail;

import com.example.yaratech.yaratube.data.model.Product;
import com.example.yaratech.yaratube.data.source.ApiResult;
import com.example.yaratech.yaratube.data.source.ProductDetailRepository;

public class ProductDetailPresenter implements ProductDetailContract.Presenter {

    private ProductDetailRepository productDetailRepository;
    private ProductDetailContract.View mView;

    public ProductDetailPresenter(ProductDetailContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void getProductDetail(Product product) {

        productDetailRepository = new ProductDetailRepository();
        mView.showProgressBar();

        productDetailRepository.getProductDetail(product, new ApiResult<Product>() {

            @Override
            public void onSuccess(Product result) {
                mView.hideProgressBar();
                mView.showProductDetail(result);
            }

            @Override
            public void onError(String massage) {
                mView.hideProgressBar();
                mView.showMessage(massage);
            }
        });
    }
}
