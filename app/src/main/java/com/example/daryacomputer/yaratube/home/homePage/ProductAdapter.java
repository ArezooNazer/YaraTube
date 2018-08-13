package com.example.daryacomputer.yaratube.home.homePage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.daryacomputer.yaratube.R;
import com.example.daryacomputer.yaratube.data.model.Product;
import com.example.daryacomputer.yaratube.data.source.ServiceGenerator;
import com.example.daryacomputer.yaratube.data.source.UpdateListData;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> implements UpdateListData<List<Product>>{

    private List<Product> productList = new ArrayList<>();
    private Context context;

    public ProductAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_product_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        holder.onBind(getItem(position));
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


    class ProductViewHolder extends RecyclerView.ViewHolder {

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



}
