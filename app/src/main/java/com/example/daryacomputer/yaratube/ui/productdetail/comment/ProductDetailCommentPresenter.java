package com.example.daryacomputer.yaratube.ui.productdetail.comment;

import com.example.daryacomputer.yaratube.data.model.Comment;
import com.example.daryacomputer.yaratube.data.model.Product;
import com.example.daryacomputer.yaratube.data.source.ApiResult;
import com.example.daryacomputer.yaratube.data.source.ProductDetailCommentRepository;

import java.util.List;

public class ProductDetailCommentPresenter implements ProductDetailCommentContract.Presenter {

   private ProductDetailCommentContract.View mView;
   private ProductDetailCommentRepository productDetailCommentRepository;

    public ProductDetailCommentPresenter(ProductDetailCommentContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void getCommentList(Product product) {

        productDetailCommentRepository = new ProductDetailCommentRepository();

        mView.showProgressBar();
        productDetailCommentRepository.getCommentList(product, new ApiResult<List<Comment>>() {
            @Override
            public void onSuccess(List<Comment> result) {

                mView.hideProgressBar();
                mView.showProductDetailCommentList(result);
            }

            @Override
            public void onError(String massage) {
                mView.ShowMassage("error!");
            }
        });

    }
}
