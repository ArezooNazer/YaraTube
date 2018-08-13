package com.example.daryacomputer.yaratube.home.homePage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.daryacomputer.yaratube.R;
import com.example.daryacomputer.yaratube.data.model.Headeritem;
import com.example.daryacomputer.yaratube.data.model.Product;
import com.example.daryacomputer.yaratube.data.source.ServiceGenerator;
import com.example.daryacomputer.yaratube.data.source.UpdateListData;

import java.util.ArrayList;
import java.util.List;

public class HomeHeaderAdapter extends RecyclerView.Adapter<HomeHeaderViewHolder> implements UpdateListData<List<Headeritem>> {

    private List<Headeritem> headerItemList = new ArrayList<>();
    private Context context;

    public HomeHeaderAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public HomeHeaderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeHeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_header_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeHeaderViewHolder holder, int position) {
        String url = ServiceGenerator.BASE_URL + getItem(position).getAvatar().getHdpi();
        Glide.with(context).load(url).into(holder.headerImageView);

    }
    private Headeritem getItem(int position) {
        return headerItemList.get(position);
    }

    @Override
    public int getItemCount() {
        return (null != headerItemList ? headerItemList.size():0);
    }

    @Override
    public void updateData(List<Headeritem> data) {
        headerItemList = data;
        notifyDataSetChanged();
    }
}
