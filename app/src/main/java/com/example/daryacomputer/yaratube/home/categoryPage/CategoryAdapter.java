package com.example.daryacomputer.yaratube.home.categoryPage;


import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.daryacomputer.yaratube.R;

import com.example.daryacomputer.yaratube.data.model.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    private List<Category> categoryList;
    private Context context;

    public CategoryAdapter(List<Category> categoryList, Context context) {
        this.categoryList = categoryList;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.onBind(getItem(position));

//        Glide.with(context).load(uri).into(holder.categoryAvatar);

    }

    @Override
    public int getItemCount() {
        return (null != categoryList ? categoryList.size() : 0);
    }

    private Category getItem(int position) {
        return categoryList.get(position);
    }

    public void updateData(List<Category> categories) {
        categoryList = categories;
        notifyDataSetChanged();

    }
}
