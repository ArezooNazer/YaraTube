package com.example.daryacomputer.yaratube.ui.home.categoryPage;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.daryacomputer.yaratube.R;
import com.example.daryacomputer.yaratube.data.model.Category;
import com.example.daryacomputer.yaratube.data.source.UpdateListData;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> implements UpdateListData<List<Category>>{

    private static String TAG = CategoryAdapter.class.getName();

    private CategoryContract.OnCategoryItemListener onCategoryItemListener ;
    private List<Category> categoryList;
    private Context context;

    public CategoryAdapter(List<Category> categoryList, Context context , CategoryContract.OnCategoryItemListener onCategoryItemListener) {
        this.categoryList = categoryList;
        this.context = context;
        this.onCategoryItemListener = onCategoryItemListener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {

        holder.onBind(getItem(position) , position ,categoryList ,onCategoryItemListener );
    }

    @Override
    public int getItemCount() {
        return (null != categoryList ? categoryList.size() : 0);
    }

    private Category getItem(int position) {
        return categoryList.get(position);
    }


    @Override
    public void updateData(List<Category> categories) {
        categoryList = categories;
        notifyDataSetChanged();
    }
}
