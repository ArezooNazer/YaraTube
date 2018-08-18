package com.example.daryacomputer.yaratube.ui.home.categoryPage;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.daryacomputer.yaratube.R;
import com.example.daryacomputer.yaratube.data.model.Category;
import com.example.daryacomputer.yaratube.ui.productlist.ProductListContract;

import java.util.List;

public class CategoryViewHolder extends RecyclerView.ViewHolder {

    private TextView categoryTitle;
    private ImageView categoryAvatar;



    public CategoryViewHolder(View itemView) {
        super(itemView);

        categoryTitle = itemView.findViewById(R.id.categoryTitle);
        categoryAvatar = itemView.findViewById(R.id.categoryAvatar);

    }

    public void onBind(Category category ,final int i ,final List<Category> categoryList ,final CategoryContract.OnCategoryItemListener onCategoryItemListener) {

        categoryTitle.setText(category.getTitle());

        String url =  category.getAvatarUrl();
        Glide.with(itemView.getContext()).load(url).into(categoryAvatar);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCategoryItemListener.onCategoryItemClick(categoryList.get(i));
            }
        });
    }
}
