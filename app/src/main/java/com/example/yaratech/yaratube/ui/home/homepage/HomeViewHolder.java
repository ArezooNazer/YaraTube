package com.example.yaratech.yaratube.ui.home.homepage;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.yaratech.yaratube.MainActivity;
import com.example.yaratech.yaratube.R;
import com.example.yaratech.yaratube.util.TransferToFragment;
import com.example.yaratech.yaratube.data.model.Homeitem;
import com.example.yaratech.yaratube.data.model.Product;
import com.example.yaratech.yaratube.ui.productgrid.ProductGridContract;

public class HomeViewHolder extends RecyclerView.ViewHolder implements ProductGridContract.OnProductListItemListener{

    private TextView eachProductListTitle;
    private RecyclerView eachProductListRecyclerView;
    private TransferToFragment goToProductDetailFragment;
    private ProductGridContract.OnProductListItemListener onProductListItemListener;

    public HomeViewHolder(View itemView) {
        super(itemView);
        eachProductListTitle = itemView.findViewById(R.id.homeListTitle);
        eachProductListRecyclerView = itemView.findViewById(R.id.homeListRecyclerView);
    }

    public void onBind(Homeitem homeItem , Context context , ProductGridContract.OnProductListItemListener onProductListItemListener) {

        if (context instanceof MainActivity) {
            goToProductDetailFragment = (TransferToFragment) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement OnMainActivityCallback!");
        }



        this.onProductListItemListener = onProductListItemListener;
        eachProductListTitle.setText(homeItem.getTitle());

        eachProductListRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        HomeProductAdapter productAdapter = new HomeProductAdapter(context , this );
        productAdapter.updateData(homeItem.getProducts());
        eachProductListRecyclerView.setAdapter(productAdapter);

    }

    @Override
    public void onProductListItemClick(Product product) {

        goToProductDetailFragment.goToProductDetailFragment(product);
    }
}