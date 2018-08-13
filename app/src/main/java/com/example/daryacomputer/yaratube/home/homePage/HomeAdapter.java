package com.example.daryacomputer.yaratube.home.homePage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.daryacomputer.yaratube.R;
import com.example.daryacomputer.yaratube.data.model.Headeritem;
import com.example.daryacomputer.yaratube.data.model.Homeitem;
import com.example.daryacomputer.yaratube.data.model.Store;
import com.example.daryacomputer.yaratube.data.source.UpdateListData;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements UpdateListData<Store> {

    private List<Homeitem> homeItemList;
    private List<Headeritem> headerItemList;
    Context context;

    final static int HEADER_VIEW_HOLDER =0;
    final static int Home_ITEM_VIEW_HOLDER =1;


    public HomeAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType == HEADER_VIEW_HOLDER ){

            return new HeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_header,parent,false));
        }
        else if(viewType == Home_ITEM_VIEW_HOLDER ){

            return new HomeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_list_row,parent,false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if(position == HEADER_VIEW_HOLDER ){

            ((HeaderViewHolder) holder).onBind(context,headerItemList);

        }
        else if(position == Home_ITEM_VIEW_HOLDER ){

            ((HomeViewHolder) holder).onBind(homeItemList.get(position),context);

        }
    }


    @Override
    public int getItemCount() {

        if( homeItemList == null && headerItemList == null)
            return 0;

        else if (homeItemList != null && headerItemList == null)
            return headerItemList.size();

        else if (homeItemList == null && headerItemList != null)
            return 1;

        else
            return homeItemList.size();
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

    @Override
    public void updateData(Store data) {
        this.headerItemList = data.getHeaderitem();
        this.homeItemList = data.getHomeitem();
        notifyDataSetChanged();
    }
}
