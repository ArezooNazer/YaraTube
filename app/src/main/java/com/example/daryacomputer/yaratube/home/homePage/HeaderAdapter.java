package com.example.daryacomputer.yaratube.home.homePage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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

public class HeaderAdapter extends RecyclerView.Adapter<HeaderAdapter.HeaderItemViewHolder> implements UpdateListData<List<Headeritem>> {

    private List<Headeritem> headerItemList = new ArrayList<>();
    private Context context;

    public HeaderAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public HeaderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HeaderItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_header_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HeaderItemViewHolder holder, int position) {
        holder.onBind(getItem(position));
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

    class HeaderItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView headerImage;

        HeaderItemViewHolder(View itemView) {
            super(itemView);
            headerImage = itemView.findViewById(R.id.headerImageItem);
        }

        void onBind(Headeritem headeritem) {

           String url = headeritem.getAvatarUrl();
            Glide.with(itemView.getContext()).load(url).into(headerImage);
        }
    }
}
