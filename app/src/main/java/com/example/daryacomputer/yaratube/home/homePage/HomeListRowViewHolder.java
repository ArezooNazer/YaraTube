package com.example.daryacomputer.yaratube.home.homePage;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.daryacomputer.yaratube.R;
import com.example.daryacomputer.yaratube.data.model.HomeItem;

public class HomeListRowViewHolder extends RecyclerView.ViewHolder {

    private TextView eachProductListTitle;
    private RecyclerView eachProductListRecyclerView;

    public HomeListRowViewHolder(View itemView) {
        super(itemView);
        eachProductListTitle = itemView.findViewById(R.id.homeListTitle);
        eachProductListRecyclerView = itemView.findViewById(R.id.homeListRecyclerView);
    }

    public void onBind(HomeItem homeItem , Context context) {
        eachProductListTitle.setText(homeItem.getTitle());
        eachProductListRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        HomeListAdapter adapter = new HomeListAdapter();
        eachProductListRecyclerView.setAdapter(adapter);
    }
}
