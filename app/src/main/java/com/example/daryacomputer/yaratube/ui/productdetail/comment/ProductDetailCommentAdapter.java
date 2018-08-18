package com.example.daryacomputer.yaratube.ui.productdetail.comment;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.daryacomputer.yaratube.R;
import com.example.daryacomputer.yaratube.data.model.Comment;
import com.example.daryacomputer.yaratube.data.source.UpdateListData;

import java.util.List;

public class ProductDetailCommentAdapter extends RecyclerView.Adapter<ProductDetailCommentAdapter.ProductDetailCommentViewHolder> implements UpdateListData<List<Comment>> {

    private List<Comment> commentList;


    public ProductDetailCommentAdapter(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public ProductDetailCommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductDetailCommentViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_detail_comment_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductDetailCommentViewHolder holder, int position) {

        holder.onBind(getItem(position));
    }

    private Comment getItem(int position){return commentList.get(position);}

    @Override
    public int getItemCount() {
        return (null != commentList? commentList.size():0);
    }

    @Override
    public void updateData(List<Comment> data) {
        commentList = data;
        notifyDataSetChanged();
    }

    public class ProductDetailCommentViewHolder extends RecyclerView.ViewHolder {

        private TextView commentUserName;
        private TextView commentTextView;

        public ProductDetailCommentViewHolder(View itemView) {
            super(itemView);

            commentUserName = itemView.findViewById(R.id.productDetailTvUserName);
            commentTextView = itemView.findViewById(R.id.productDetailTvComment);
        }

        public void onBind(Comment comment) {

            commentUserName.setText(comment.getUser());
            commentTextView.setText(comment.getCommentText());
        }
    }
}
