package com.example.daryacomputer.yaratube.home.homePage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.daryacomputer.yaratube.R;
import com.example.daryacomputer.yaratube.data.model.Headeritem;
import com.example.daryacomputer.yaratube.data.source.UpdateListData;

import java.util.ArrayList;
import java.util.List;

public class HeaderViewPagerAdapter extends FragmentStatePagerAdapter implements UpdateListData<List<Headeritem>>{

    private List<Headeritem> headerItemList = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context mContext;

    public HeaderViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return (null != headerItemList ? headerItemList.size() : 0);
    }


    @Override
    public Fragment getItem(int i) {
        i = getCount() - i - 1;
        return HeaderItemFragment.newInstance( headerItemList.get(i));
    }


    @Override
    public void updateData(List<Headeritem> data) {
        headerItemList = data;
        notifyDataSetChanged();
    }
}
