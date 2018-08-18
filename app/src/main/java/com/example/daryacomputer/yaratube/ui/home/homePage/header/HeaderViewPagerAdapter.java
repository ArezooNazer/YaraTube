package com.example.daryacomputer.yaratube.ui.home.homePage.header;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.example.daryacomputer.yaratube.data.model.Headeritem;
import com.example.daryacomputer.yaratube.data.source.UpdateListData;
import com.example.daryacomputer.yaratube.ui.home.homePage.header.HeaderItemFragment;

import java.util.ArrayList;
import java.util.List;

public class HeaderViewPagerAdapter extends FragmentStatePagerAdapter implements UpdateListData<List<Headeritem>>{

    private List<Headeritem> headerItemList = new ArrayList<>();

    public HeaderViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return (null != headerItemList ? headerItemList.size() : 0);
    }


    @Override
    public Fragment getItem(int i) {
//        i = getCount() - i - 1; //load images last to first
        return HeaderItemFragment.newInstance( headerItemList.get(i));
    }


    @Override
    public void updateData(List<Headeritem> data) {
        headerItemList = data;
        notifyDataSetChanged();
    }
}
