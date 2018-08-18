package com.example.daryacomputer.yaratube.ui.productdetail.comment;

import com.example.daryacomputer.yaratube.data.model.Comment;
import com.example.daryacomputer.yaratube.data.model.Product;

import java.util.List;

public interface ProductDetailCommentContract {

    interface View{
        void showProductDetailCommentList(List<Comment> commentList);
        void ShowMassage(String message);

        void showProgressBar();
        void hideProgressBar();
    }

    interface Presenter{
        void getCommentList(Product product);
    }
}
