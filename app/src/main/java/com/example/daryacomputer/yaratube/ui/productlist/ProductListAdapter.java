package com.example.daryacomputer.yaratube.ui.productlist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.daryacomputer.yaratube.R;
import com.example.daryacomputer.yaratube.data.model.Product;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListViewHolder> {

    private List<Product> productList;
    private Context context;
    private ProductListContract.OnProductListItemListener onProductListItemListener;

    public ProductListAdapter(List<Product> productList, Context context , ProductListContract.OnProductListItemListener onProductListItemListener ) {
        this.productList = productList;
        this.context = context;
        this.onProductListItemListener = onProductListItemListener;
    }

    @NonNull
    @Override
    public ProductListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item_grid,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListViewHolder holder, int position) {

        holder.onBind(getItem(position),onProductListItemListener );
    }

    private Product getItem(int position){
       return productList.get(position);
    }

    @Override
    public int getItemCount() {
        return (null != productList? productList.size():0);
    }

    public void updateData(List<Product> productList){
        this.productList = productList;
        notifyDataSetChanged();
    }
}
