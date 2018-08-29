package com.example.daryacomputer.yaratube.ui.productdetail.comment;

import android.support.v4.app.DialogFragment;

import com.example.daryacomputer.yaratube.data.model.Comment;
import com.example.daryacomputer.yaratube.data.model.Product;
import com.example.daryacomputer.yaratube.data.model.SendComment;
import com.example.daryacomputer.yaratube.data.source.ApiResult;
import com.example.daryacomputer.yaratube.data.source.CommentRepository;

import java.util.List;

public class CommentPresenter implements CommentContract.Presenter {

   private CommentContract.View mView;
   private CommentRepository commentRepository;

    public CommentPresenter(CommentContract.View mView) {
        this.mView = mView;
        commentRepository = new CommentRepository();
    }

    @Override
    public void getCommentList(Product product) {

        mView.showProgressBar();
        commentRepository.getCommentList(product, new ApiResult<List<Comment>>() {
            @Override
            public void onSuccess(List<Comment> result) {

                mView.hideProgressBar();
                mView.showCommentList(result);
            }

            @Override
            public void onError(String massage) {
                mView.ShowMassage("error!");
            }
        });

    }

    @Override
    public void sendComment(String title, int score, String commentText, int productId, String token) {

        commentRepository.sendCommentRepo(title, score, commentText,productId, token, new ApiResult<SendComment>() {
            @Override
            public void onSuccess(SendComment result) {
                mView.ShowMassage("نظر شما بعد از بررسی ثبت خواهد شد");
                mView.commentIsSuccessfullySent();
                }

            @Override
            public void onError(String massage) {
                mView.ShowMassage("دوباره تلاش کنید");
            }
        });



    }


}
