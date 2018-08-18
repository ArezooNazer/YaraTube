package com.example.daryacomputer.yaratube.home.homePage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.example.daryacomputer.yaratube.R;
import com.example.daryacomputer.yaratube.data.model.Headeritem;
import org.parceler.Parcels;

public class HeaderItemFragment extends Fragment {

    private Headeritem headeritem;

    public HeaderItemFragment( ) {
        // Required empty public constructor

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        headeritem = Parcels.unwrap(getArguments().getParcelable("headeritem"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.home_header_item, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView headerImageView;
        headerImageView = view.findViewById(R.id.headerImageItem);

        String url = headeritem.getAvatarUrl();
        Glide.with(view.getContext()).load(url).into(headerImageView);

    }

    public static HeaderItemFragment newInstance(Headeritem headeritem) {

        Bundle args = new Bundle();
        args.putParcelable("headeritem" ,Parcels.wrap( headeritem));

        HeaderItemFragment fragment = new HeaderItemFragment();
        fragment.setArguments(args);
        return fragment;
    }


}
