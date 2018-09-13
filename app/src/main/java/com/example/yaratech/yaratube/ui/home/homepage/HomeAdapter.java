package com.example.yaratech.yaratube.ui.home.homepage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.yaratech.yaratube.MainActivity;
import com.example.yaratech.yaratube.R;
import com.example.yaratech.yaratube.util.TransferToFragment;
import com.example.yaratech.yaratube.data.model.Homeitem;
import com.example.yaratech.yaratube.data.model.Product;
import com.example.yaratech.yaratube.data.model.Store;
import com.example.yaratech.yaratube.data.source.UpdateListData;
import com.example.yaratech.yaratube.ui.home.homepage.header.HeaderViewHolder;
import com.example.yaratech.yaratube.ui.productgrid.ProductGridContract;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements UpdateListData<Store> , ProductGridContract.OnProductListItemListener{

    private List<Homeitem> homeItemList;
    private List<Product> headerItemList;
    Context context;
    FragmentManager fm;
    ProductGridContract.OnProductListItemListener onProductListItemListener;

    private TransferToFragment goToProductDetailFragment;

    final static int HEADER_VIEW_HOLDER =1;
    final static int Home_ITEM_VIEW_HOLDER =2;


    public HomeAdapter(Context context ,FragmentManager fm , ProductGridContract.OnProductListItemListener onProductListItemListener) {

        this.onProductListItemListener = onProductListItemListener;
        this.context = context;
        this.fm = fm;

        if (context instanceof MainActivity) {
            goToProductDetailFragment = (TransferToFragment) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement OnMainActivityCallback!");
        }
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

        int viewType = holder.getItemViewType();

        if(viewType == HEADER_VIEW_HOLDER ){

            ((HeaderViewHolder) holder).onBind(fm,headerItemList);

        }
        else if(viewType == Home_ITEM_VIEW_HOLDER ){

            ((HomeViewHolder) holder).onBind(homeItemList.get(position - 1),context, this);

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
            return 1 + homeItemList.size() ;
    }


    @Override
    public int getItemViewType(int position) {

        if(position == 0)
            return HEADER_VIEW_HOLDER;
        return Home_ITEM_VIEW_HOLDER;
    }

    @Override
    public void updateData(Store data) {
        this.headerItemList = data.getHeaderitem();
        this.homeItemList = data.getHomeitem();
        notifyDataSetChanged();
    }

    @Override
    public void onProductListItemClick(Product product) {
        goToProductDetailFragment.goToProductDetailFragment(product);
    }
}