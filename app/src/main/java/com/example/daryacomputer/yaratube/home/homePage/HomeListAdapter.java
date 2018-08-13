package com.example.daryacomputer.yaratube.home.homePage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.daryacomputer.yaratube.R;
import com.example.daryacomputer.yaratube.data.model.Headeritem;
import com.example.daryacomputer.yaratube.data.model.Homeitem;
import com.example.daryacomputer.yaratube.data.model.Store;
import com.example.daryacomputer.yaratube.data.source.UpdateListData;

import java.util.List;

public class HomeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements UpdateListData<List<Homeitem>>{

    private List<Homeitem> homeItemList;
    private List<Headeritem> headerItemList;
    Context context;

    final static int HEADER_VIEW_HOLDER =0;
    final static int Home_ITEM_VIEW_HOLDER =1;


    public HomeListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType == HEADER_VIEW_HOLDER ){

            return new HomeHeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_header_item,parent,false));
        }
        else if(viewType == Home_ITEM_VIEW_HOLDER ){

            return new HomeListRowViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_list_row,parent,false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if(position == HEADER_VIEW_HOLDER ){

            ( (HomeHeaderViewHolder) holder).onBind(getItem(position),context,homeItemList , position);

        }
        else if(position == Home_ITEM_VIEW_HOLDER ){

            ((HomeListRowViewHolder) holder).onBind(getItem(position),context,homeItemList , position);

        }
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

    @Override
    public int getItemViewType(int position) {

        if(position == 0){
            return HEADER_VIEW_HOLDER;
        }
        else if(position == 1) {
            return Home_ITEM_VIEW_HOLDER;
        }

        return super.getItemViewType(position);
    }
}
