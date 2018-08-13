package com.example.daryacomputer.yaratube.home.homePage;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.daryacomputer.yaratube.R;
import com.example.daryacomputer.yaratube.data.model.Headeritem;

import java.util.List;

public class HeaderViewHolder extends RecyclerView.ViewHolder {

    RecyclerView headerRecyclerView;

    public HeaderViewHolder(View itemView) {
        super(itemView);
        headerRecyclerView = itemView.findViewById(R.id.headerRecyclerView);
    }

    public void onBind(Context context, List<Headeritem> headeritems ){

        headerRecyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        HeaderAdapter headerAdapter = new HeaderAdapter(context);
        headerAdapter.updateData(headeritems);
        headerRecyclerView.setAdapter(headerAdapter);
    }
}
