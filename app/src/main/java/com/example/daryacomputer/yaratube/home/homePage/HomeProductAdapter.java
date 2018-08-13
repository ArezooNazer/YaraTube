package com.example.daryacomputer.yaratube.home.homePage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.daryacomputer.yaratube.R;
import com.example.daryacomputer.yaratube.data.model.Product;
import com.example.daryacomputer.yaratube.data.model.Store;
import com.example.daryacomputer.yaratube.data.source.ServiceGenerator;
import com.example.daryacomputer.yaratube.data.source.UpdateListData;

import java.util.ArrayList;
import java.util.List;

public class HomeProductAdapter extends RecyclerView.Adapter<HomeProductViewHolder> implements UpdateListData<List<Product>>{

    private List<Product> productList = new ArrayList<>();
    private Context context;

    public HomeProductAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public HomeProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeProductViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_product_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeProductViewHolder holder, int position) {

        holder.onBind(getItem(position));

        String url = ServiceGenerator.BASE_URL + getItem(position).getAvatar().getHdpi();
        Glide.with(context).load(url).into(holder.productImage);

    }

    private Product getItem(int position) {
        return productList.get(position);
    }

    @Override
    public int getItemCount() {
        return (null != productList ? productList.size() : 0);
    }

    @Override
    public void updateData(List<Product> productList) {

        this.productList = productList;
        notifyDataSetChanged();

    }


}
