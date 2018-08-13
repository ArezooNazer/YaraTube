package com.example.daryacomputer.yaratube.home.homePage;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.daryacomputer.yaratube.R;

public class HomeHeaderViewHolder extends RecyclerView.ViewHolder {

    ImageView headerImageView;

    public HomeHeaderViewHolder(View itemView) {
        super(itemView);
        headerImageView = itemView.findViewById(R.id.headerImageItem);
    }
}
