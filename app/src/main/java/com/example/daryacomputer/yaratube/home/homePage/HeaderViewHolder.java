package com.example.daryacomputer.yaratube.home.homePage;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.HeaderViewListAdapter;

import com.example.daryacomputer.yaratube.R;
import com.example.daryacomputer.yaratube.data.model.Headeritem;

import java.util.List;

public class HeaderViewHolder extends RecyclerView.ViewHolder {

//    RecyclerView headerRecyclerView;
    ViewPager viewPager;

    public HeaderViewHolder(View itemView) {
        super(itemView);
//        headerRecyclerView = itemView.findViewById(R.id.headerRecyclerView);

        viewPager = itemView.findViewById(R.id.headerViewPager);
    }

    public void onBind(FragmentManager fm, List<Headeritem> headeritems ){

//        headerRecyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
//        HeaderAdapter headerAdapter = new HeaderAdapter(context);
//        headerAdapter.updateData(headeritems);
//        headerRecyclerView.setAdapter(headerAdapter);

       HeaderViewPagerAdapter headerViewPagerAdapter = new HeaderViewPagerAdapter(fm);
       headerViewPagerAdapter.updateData(headeritems);
       viewPager.setAdapter(headerViewPagerAdapter);


    }
}
