package com.example.yaratech.yaratube.ui.home.moreitem;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yaratech.yaratube.MainActivity;
import com.example.yaratech.yaratube.R;
import com.example.yaratech.yaratube.data.source.LoginRepository;
import com.example.yaratech.yaratube.util.TransferToFragment;


public class MoreItemFragment extends Fragment {

    private TextView profile, aboutUs, contactUs, logOut;
    private TransferToFragment transferToFragment;

    public MoreItemFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity)
            transferToFragment = (TransferToFragment) context;
        else
            throw new ClassCastException(context.toString() + " must implement OnMainActivityCallback!");
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_more_item, container, false);

       profile = view.findViewById(R.id.profile);
       aboutUs = view.findViewById(R.id.aboutUs);
       contactUs = view.findViewById(R.id.contactUs);
       logOut = view.findViewById(R.id.logOut);

       return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        profile.setOnClickListener(new MyOnClickListener());
    }

    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if(LoginRepository.isLogin())
                transferToFragment.goToProfileFragment();
            else
                transferToFragment.goToMainLoginDialogFragment();

        }
    }
}
