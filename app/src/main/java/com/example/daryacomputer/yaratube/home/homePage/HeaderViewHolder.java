package com.example.daryacomputer.yaratube.home.homePage;


import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.daryacomputer.yaratube.R;
import com.example.daryacomputer.yaratube.data.model.Headeritem;

import java.util.List;

public class HeaderViewHolder extends RecyclerView.ViewHolder {

    ViewPager viewPager;

    public HeaderViewHolder(View itemView) {
        super(itemView);
        viewPager = itemView.findViewById(R.id.headerViewPager);
    }

    public void onBind(FragmentManager fm, List<Headeritem> headeritems ){

       HeaderViewPagerAdapter headerViewPagerAdapter = new HeaderViewPagerAdapter(fm);
       headerViewPagerAdapter.updateData(headeritems);
       viewPager.setAdapter(headerViewPagerAdapter);
       viewPager.setRotationY(180);//swipe viewpager rtl


    }
}
