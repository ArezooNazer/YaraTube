package com.example.yaratech.yaratube.data.source;

import android.support.v7.util.DiffUtil;

import com.example.yaratech.yaratube.data.model.Product;

import java.util.List;

public class DiffUtilCB extends DiffUtil.Callback {

    private final List<Product> oldItems;
    private final List<Product> newItems;

    public DiffUtilCB(List<Product> oldItems, List<Product> newItems) {
        this.oldItems = oldItems;
        this.newItems = newItems;
    }

    @Override
    public int getOldListSize() {
        return oldItems.size();
    }

    @Override
    public int getNewListSize() {
        return newItems.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldItems.get(oldItemPosition).getId().equals(newItems.get(newItemPosition).getId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldItems.get(oldItemPosition).equals(newItems.get(newItemPosition));
    }
}
