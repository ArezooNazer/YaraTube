package com.example.daryacomputer.yaratube.home.homePage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.daryacomputer.yaratube.R;
import com.example.daryacomputer.yaratube.data.model.Homeitem;
import com.example.daryacomputer.yaratube.data.source.UpdateListData;

import java.util.List;

public class HomeListAdapter extends RecyclerView.Adapter<HomeListRowViewHolder> implements UpdateListData<List<Homeitem>>{

    private List<Homeitem> homeItemList;
    Context context;

    public HomeListAdapter(List<Homeitem> homeItemList, Context context) {
        this.homeItemList = homeItemList;
        this.context = context;
    }

    @NonNull
    @Override
    public HomeListRowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeListRowViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_list_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeListRowViewHolder holder, int position) {

        holder.onBind(getItem(position),context);
    }

    private Homeitem getItem(int position){
        return  homeItemList.get(position);
    }

    @Override
    public int getItemCount() {
        return (null != homeItemList ? homeItemList.size() : 0);
    }

    @Override
    public void updateData(List<Homeitem> homeItems) {

       homeItemList = homeItems;
       notifyDataSetChanged();
    }
}
