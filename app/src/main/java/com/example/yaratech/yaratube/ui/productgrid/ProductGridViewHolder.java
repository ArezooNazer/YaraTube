package com.example.yaratech.yaratube.ui.productgrid;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yaratech.yaratube.R;
import com.example.yaratech.yaratube.data.model.Product;

public class ProductGridViewHolder extends RecyclerView.ViewHolder{

    private ImageView productAvatar;
    private TextView productTitle;
    private TextView productDesc;

    public ProductGridViewHolder(View itemView) {
        super(itemView);

        productAvatar = itemView.findViewById(R.id.productListAvatar);
        productTitle = itemView.findViewById(R.id.productListTitle);
        productDesc = itemView.findViewById(R.id.productListDescription);
    }

    public void onBind(final Product product , final ProductGridContract.OnProductListItemListener onProductListItemListener){
        productTitle.setText(product.getName());
        productDesc.setText(product.getShortDescription());

        String url =  product.getAvatarUrl();
        Glide.with(itemView.getContext()).load(url).into(productAvatar);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onProductListItemListener.onProductListItemClick(product);
            }
        });

    }
}
