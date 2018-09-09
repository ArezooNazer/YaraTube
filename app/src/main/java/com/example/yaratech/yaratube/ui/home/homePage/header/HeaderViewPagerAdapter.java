package com.example.yaratech.yaratube.ui.home.homePage.header;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.example.yaratech.yaratube.data.model.Headeritem;
import com.example.yaratech.yaratube.data.model.Producer;
import com.example.yaratech.yaratube.data.model.Product;
import com.example.yaratech.yaratube.data.source.UpdateListData;
import com.example.yaratech.yaratube.ui.home.homePage.header.HeaderItemFragment;

import java.util.ArrayList;
import java.util.List;

public class HeaderViewPagerAdapter extends FragmentStatePagerAdapter implements UpdateListData<List<Product>>{

    private List<Product> headerItemList = new ArrayList<>();

    public HeaderViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        return HeaderItemFragment.newInstance( headerItemList.get(i));
    }

    @Override
    public int getCount() {
        return (null != headerItemList ? headerItemList.size() : 0);
    }

    @Override
    public void updateData(List<Product> data) {
        headerItemList = data;
        notifyDataSetChanged();
    }
}
