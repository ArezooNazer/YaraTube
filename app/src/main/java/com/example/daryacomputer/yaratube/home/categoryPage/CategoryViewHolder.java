package com.example.daryacomputer.yaratube.home.categoryPage;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.daryacomputer.yaratube.R;
import com.example.daryacomputer.yaratube.data.model.Category;

public class CategoryViewHolder extends RecyclerView.ViewHolder {
    TextView categoryTitle;
    ImageView categoryAvatar;

    public CategoryViewHolder(View itemView) {
        super(itemView);
        categoryTitle = itemView.findViewById(R.id.categoryTitle);
        categoryAvatar = itemView.findViewById(R.id.categoryAvatar);
    }

    public void onBind(Category category) {

        categoryTitle.setText(category.getTitle());

        String url =  category.getAvatarUrl();
        Glide.with(itemView.getContext()).load(url).into(categoryAvatar);
    }
}
