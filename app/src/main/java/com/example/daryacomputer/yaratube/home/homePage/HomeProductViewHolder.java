package com.example.daryacomputer.yaratube.home.homePage;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.daryacomputer.yaratube.R;
import com.example.daryacomputer.yaratube.data.model.Product;

public class HomeProductViewHolder extends RecyclerView.ViewHolder {

    public ImageView productImage;
    private TextView productName;
    private TextView productDescription;

    public HomeProductViewHolder(View itemView) {
        super(itemView);
        productImage = itemView.findViewById(R.id.productAvatar);
        productName = itemView.findViewById(R.id.productName);
        productDescription = itemView.findViewById(R.id.productDescription);
    }

    public void onBind(Product product){

        productName.setText(product.getName());
        productDescription.setText(product.getDescription());
    }
}
