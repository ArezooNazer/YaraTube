package com.example.daryacomputer.yaratube.ui.productgrid;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.daryacomputer.yaratube.R;
import com.example.daryacomputer.yaratube.data.model.Product;
import com.example.daryacomputer.yaratube.data.source.DiffUtilCB;

import java.util.ArrayList;
import java.util.List;

public class ProductGridAdapter extends RecyclerView.Adapter<ProductGridViewHolder> {

    private List<Product> oldItems;
    private Context context;
    private ProductGridContract.OnProductListItemListener onProductListItemListener;

    public ProductGridAdapter(List<Product> productList, Context context , ProductGridContract.OnProductListItemListener onProductListItemListener ) {
        this.oldItems = productList;
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
       return oldItems.get(position);
    }

    @Override
    public int getItemCount() {
        return (null != oldItems? oldItems.size():0);
    }


    public void firstDataLoad(List<Product> productList){
        this.oldItems = productList;
        notifyDataSetChanged();

    }

    public void addItem(Product product)  {
        oldItems.add(product);
        notifyItemInserted(oldItems.size() - 1);
    }

    //in this case using this method is best practice
    public void updateData(List<Product> products){

        for (Product product : products) {
            addItem(product);
        }
    }

    //if u need delete, update, replace an item, using this method is best practice
    public void updateItems(List<Product> newItems) {
        List<Product> newList=new ArrayList<>(oldItems);
        newList.addAll(newItems);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtilCB(oldItems, newList));
        diffResult.dispatchUpdatesTo(this);
        oldItems.addAll(newItems);
    }
}
