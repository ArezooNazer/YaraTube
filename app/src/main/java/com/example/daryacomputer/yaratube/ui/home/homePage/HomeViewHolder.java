package com.example.daryacomputer.yaratube.ui.home.homePage;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.daryacomputer.yaratube.R;
import com.example.daryacomputer.yaratube.data.model.Homeitem;

public class HomeViewHolder extends RecyclerView.ViewHolder {

    private TextView eachProductListTitle;
    private RecyclerView eachProductListRecyclerView;

    public HomeViewHolder(View itemView) {
        super(itemView);
        eachProductListTitle = itemView.findViewById(R.id.homeListTitle);
        eachProductListRecyclerView = itemView.findViewById(R.id.homeListRecyclerView);
    }

    public void onBind(Homeitem homeItem , Context context ) {

        eachProductListTitle.setText(homeItem.getTitle());

        eachProductListRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        ProductAdapter productAdapter = new ProductAdapter(context);
        productAdapter.updateData(homeItem.getProducts());
        eachProductListRecyclerView.setAdapter(productAdapter);

    }
}
