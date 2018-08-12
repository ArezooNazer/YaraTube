package com.example.daryacomputer.yaratube.home.homePage;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.daryacomputer.yaratube.R;

public class HeaderViewHolder extends RecyclerView.ViewHolder {

    ImageView headerItem;

    public HeaderViewHolder(View itemView) {
        super(itemView);
        headerItem = itemView.findViewById(R.id.headerImageItem);
    }
}
