package com.example.daryacomputer.yaratube.data.source;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

public abstract class PaginationScrollListener extends RecyclerView.OnScrollListener {

    static String TAG = PaginationScrollListener.class.getName();
    GridLayoutManager GridLayoutManager;

    public PaginationScrollListener(GridLayoutManager layoutManager) {
        this.GridLayoutManager = layoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visibleItemCount = GridLayoutManager.getChildCount();
        int totalItemCount = GridLayoutManager.getItemCount();
        int firstVisibleItemPosition = GridLayoutManager.findFirstVisibleItemPosition();

        Log.d("TAG", "visibleItemCount= " + visibleItemCount + " totalItemCount= " + totalItemCount + " firstVisibleItemPosition= " + firstVisibleItemPosition);

        if (!isLoading() && !isLastPage()) {
            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0) {
                loadMoreItems();
            }
        }
    }

    protected abstract void loadMoreItems();

    public abstract boolean isLastPage();

    public abstract boolean isLoading();
}