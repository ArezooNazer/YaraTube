package com.example.daryacomputer.yaratube.ui.home.homePage;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.daryacomputer.yaratube.MainActivity;
import com.example.daryacomputer.yaratube.R;
import com.example.daryacomputer.yaratube.TransferToFragment;
import com.example.daryacomputer.yaratube.data.model.Homeitem;
import com.example.daryacomputer.yaratube.data.model.Product;
import com.example.daryacomputer.yaratube.ui.productlist.ProductListContract;

public class HomeViewHolder extends RecyclerView.ViewHolder implements ProductListContract.OnProductListItemListener{

    private TextView eachProductListTitle;
    private RecyclerView eachProductListRecyclerView;
    private TransferToFragment goToProductDetailFragment;
    private ProductListContract.OnProductListItemListener onProductListItemListener;

    public HomeViewHolder(View itemView) {
        super(itemView);
        eachProductListTitle = itemView.findViewById(R.id.homeListTitle);
        eachProductListRecyclerView = itemView.findViewById(R.id.homeListRecyclerView);
    }

    public void onBind(Homeitem homeItem , Context context , ProductListContract.OnProductListItemListener onProductListItemListener) {

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