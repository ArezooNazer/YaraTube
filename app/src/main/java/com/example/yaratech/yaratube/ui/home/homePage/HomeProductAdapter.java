package com.example.yaratech.yaratube.ui.home.homePage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yaratech.yaratube.R;
import com.example.yaratech.yaratube.data.model.Product;
import com.example.yaratech.yaratube.data.source.UpdateListData;
import com.example.yaratech.yaratube.ui.productgrid.ProductGridContract;

import java.util.List;

public class HomeProductAdapter extends RecyclerView.Adapter<HomeProductAdapter.ProductViewHolder> implements UpdateListData<List<Product>>{

    private ProductGridContract.OnProductListItemListener onProductListItemListener;
    private List<Product> productList ;
    private Context context;

    public HomeProductAdapter(Context context , ProductGridContract.OnProductListItemListener onProductListItemListener) {
        this.context = context;
        this.onProductListItemListener = onProductListItemListener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_product_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        holder.onBind(getItem(position), onProductListItemListener);
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


    class ProductViewHolder extends RecyclerView.ViewHolder{

        public ImageView productImage;
        private TextView productName;
        private TextView productDescription;

        public ProductViewHolder(View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productAvatar);
            productName = itemView.findViewById(R.id.productName);
            productDescription = itemView.findViewById(R.id.productDescription);
        }

        public void onBind(final Product product ,final ProductGridContract.OnProductListItemListener onProductListItemListener){

            String url = product.getAvatarUrl();
            Glide.with(itemView.getContext()).load(url).into(productImage);

            productName.setText(product.getName());
            productDescription.setText(product.getShortDescription());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onProductListItemListener.onProductListItemClick(product);
                }
            });

        }

    }



}