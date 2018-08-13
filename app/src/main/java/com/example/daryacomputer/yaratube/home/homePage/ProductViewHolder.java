package com.example.daryacomputer.yaratube.home.homePage;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.daryacomputer.yaratube.R;
import com.example.daryacomputer.yaratube.data.model.Product;
import com.example.daryacomputer.yaratube.data.source.ServiceGenerator;

public class ProductViewHolder extends RecyclerView.ViewHolder {

    public ImageView productImage;
    private TextView productName;
    private TextView productDescription;

    public ProductViewHolder(View itemView) {
        super(itemView);
        productImage = itemView.findViewById(R.id.productAvatar);
        productName = itemView.findViewById(R.id.productName);
        productDescription = itemView.findViewById(R.id.productDescription);
    }

    public void onBind(Product product){

        String url = ServiceGenerator.BASE_URL + product.getAvatar().getHdpi();
        Glide.with(itemView.getContext()).load(url).into(productImage);

        productName.setText(product.getName());
        productDescription.setText(product.getShortDescription());
    }
}
