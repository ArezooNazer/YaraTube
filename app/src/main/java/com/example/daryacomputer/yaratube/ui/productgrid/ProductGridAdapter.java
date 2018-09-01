package com.example.daryacomputer.yaratube.ui.productgrid;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.daryacomputer.yaratube.R;
import com.example.daryacomputer.yaratube.data.model.Product;

import java.util.List;

public class ProductGridAdapter extends RecyclerView.Adapter<ProductGridViewHolder> {

    private List<Product> productList;
    private Context context;
    private ProductGridContract.OnProductListItemListener onProductListItemListener;

    public ProductGridAdapter(List<Product> productList, Context context , ProductGridContract.OnProductListItemListener onProductListItemListener ) {
        this.productList = productList;
        this.context = context;
        this.onProductListItemListener = onProductListItemListener;
    }

    @NonNull
    @Override
    public ProductGridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductGridViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item_grid,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductGridViewHolder holder, int position) {

        holder.onBind(getItem(position),onProductListItemListener );
    }

    private Product getItem(int position){
       return productList.get(position);
    }

    @Override
    public int getItemCount() {
        return (null != productList? productList.size():0);
    }


    public void firstDataLoad(List<Product> productList){
        this.productList = productList;
        notifyDataSetChanged();

    }

    public void addItem(Product product)  {
        productList.add(product);
        notifyItemInserted(productList.size() - 1);
    }

    public void updateData(List<Product> products){

        for (Product product : products) {
            addItem(product);
        }

    }




}
