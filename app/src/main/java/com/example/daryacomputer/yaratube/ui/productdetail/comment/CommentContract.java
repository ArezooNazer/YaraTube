package com.example.daryacomputer.yaratube.ui.productdetail.comment;

import com.example.daryacomputer.yaratube.BaseView;
import com.example.daryacomputer.yaratube.data.model.Comment;
import com.example.daryacomputer.yaratube.data.model.Product;

import java.util.List;

public interface CommentContract {

    interface View extends BaseView {
        void showCommentList(List<Comment> commentList);
    }

    interface Presenter {
        void getCommentList(Product product);
        void sendComment(String title, int score, String commentText);
    }
}
