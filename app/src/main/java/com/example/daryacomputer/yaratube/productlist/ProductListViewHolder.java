package com.example.daryacomputer.yaratube.productList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.daryacomputer.yaratube.R;
import com.example.daryacomputer.yaratube.data.model.Product;

public class ProductListViewHolder extends RecyclerView.ViewHolder{

    private ImageView productAvatar;
    private TextView productTitle;
    private TextView productDesc;

    public ProductListViewHolder(View itemView) {
        super(itemView);

        productAvatar = itemView.findViewById(R.id.productListAvatar);
        productTitle = itemView.findViewById(R.id.productListTitle);
        productDesc = itemView.findViewById(R.id.productListDescription);
    }

    public void onBind(Product product, Context context){
        productTitle.setText(product.getName());
        productDesc.setText(product.getShortDescription());

        String url =  product.getAvatarUrl();
        Glide.with(itemView.getContext()).load(url).into(productAvatar);




    }
}
