package com.example.yaratech.yaratube.ui.home.homepage.header;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.yaratech.yaratube.R;
import com.example.yaratech.yaratube.util.TransferToFragment;
import com.example.yaratech.yaratube.data.model.Product;

import org.parceler.Parcels;

public class HeaderItemFragment extends Fragment {

    private Product headeritem;
    private TransferToFragment goToProductDetailFragment;

    public HeaderItemFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        goToProductDetailFragment = (TransferToFragment) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        headeritem = Parcels.unwrap(getArguments().getParcelable("headeritem"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_header_item, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView headerImageView;
        headerImageView = view.findViewById(R.id.headerImageItem);

        String url = headeritem.getAvatarUrl();
        Glide.with(view.getContext()).load(url).into(headerImageView);


        headerImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                goToProductDetailFragment.goToProductDetailFragment(headeritem,"");
            }
        });

    }

    public static HeaderItemFragment newInstance(Product headeritem) {

        Bundle args = new Bundle();
        args.putParcelable("headeritem", Parcels.wrap(headeritem));

        HeaderItemFragment fragment = new HeaderItemFragment();
        fragment.setArguments(args);
        return fragment;
    }


}
