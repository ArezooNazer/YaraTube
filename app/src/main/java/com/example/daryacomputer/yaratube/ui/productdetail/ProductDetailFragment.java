package com.example.daryacomputer.yaratube.ui.productdetail;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.daryacomputer.yaratube.MainActivity;
import com.example.daryacomputer.yaratube.R;
import com.example.daryacomputer.yaratube.util.TransferToFragment;
import com.example.daryacomputer.yaratube.data.model.Comment;
import com.example.daryacomputer.yaratube.data.model.Product;
import com.example.daryacomputer.yaratube.data.source.LoginRepository;
import com.example.daryacomputer.yaratube.ui.productdetail.comment.CommentAdapter;
import com.example.daryacomputer.yaratube.ui.productdetail.comment.CommentContract;
import com.example.daryacomputer.yaratube.ui.productdetail.comment.CommentPresenter;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailFragment extends Fragment implements CommentContract.View, ProductDetailContract.View {

    final public static String PRODUCT_DETAIL_FRAGMENT = ProductDetailFragment.class.getSimpleName();
    private ProductDetailContract.Presenter detailPresenter;
    private List<Comment> commentList = new ArrayList<>();
    public static boolean LOGIN_FROM_COMMENT = false;
    final public static String PRODUCT = "product";
    private TransferToFragment transferToFragment;
    private CommentContract.Presenter mPresenter;
    private CommentAdapter commentAdapter;
    private ProgressBar progressBar;
    private Product product;
    private ImageView playBut,  productDetailImage;


    public ProductDetailFragment() {
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

        Bundle arg = getArguments();
        if (arg == null) return;
        setProduct((Product) arg.getParcelable(PRODUCT));

        commentAdapter = new CommentAdapter(commentList);
        mPresenter = new CommentPresenter(this);
        detailPresenter = new ProductDetailPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_prodcut_detail, container, false);

        progressBar = view.findViewById(R.id.detailProgressBar);

        Toolbar mToolbar = (Toolbar) view.findViewById(R.id.toolbar);

        if (mToolbar != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);

            ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            actionBar.setTitle("جزییات محصول");
        }

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter.getCommentList(product);
        detailPresenter.getProductDetail(product);
        setProductDetailData(view);

        Button sendCommentButton = view.findViewById(R.id.sendCommentButton);
        sendCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (LoginRepository.isLogin()) {
                    transferToFragment.goToCommentDialogFragment(product.getId());
                    Log.d("TAG" ,product.getId().toString() );
                }
                else {
                    LOGIN_FROM_COMMENT = true;
                    transferToFragment.goToMainLoginDialogFragment();
                }
            }
        });

        playBut = view.findViewById(R.id.productDetailIvIcon);
        productDetailImage = view.findViewById(R.id.productDetailIv);
        videoPlayerListener();


    }

    public void setProductDetailData(View view) {

        ImageView productDetailImageView = view.findViewById(R.id.productDetailIv);
        TextView productDetailTitle = view.findViewById(R.id.productDetailTvTitle);
        TextView productDetailDesc = view.findViewById(R.id.ProductDetailTvDesc);

        String url = product.getAvatarUrl();
        Glide.with(view.getContext()).load(url).into(productDetailImageView);

        if (product.getName() != null)
            productDetailTitle.setText(product.getName());
        if (product.getShortDescription() != null)
            productDetailDesc.setText(product.getDescription());


        RecyclerView productDetailComment = view.findViewById(R.id.productDetailRvComment);
        productDetailComment.setLayoutManager(new LinearLayoutManager(getContext()));
        productDetailComment.setAdapter(commentAdapter);
    }


    public static ProductDetailFragment newInstance(Product product) {

        Bundle arg = new Bundle();
        arg.putParcelable(PRODUCT, (Parcelable) product);

        ProductDetailFragment productDetailFragment = new ProductDetailFragment();
        productDetailFragment.setArguments(arg);
        return productDetailFragment;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public void showCommentList(List<Comment> commentList) {
        commentAdapter.updateData(commentList);
    }

    @Override
    public void commentIsSuccessfullySent() {

    }

    public void ShowMassage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }


    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showProductDetail(Product product) {
        this.product = product;
    }


    public void videoPlayerListener(){

//        ctrl+shift+a : anonymous -> create inner class
        playBut.setOnClickListener(new MyOnClickListener());
        productDetailImage.setOnClickListener(new MyOnClickListener());

    }

    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            if (!LoginRepository.isLogin()) {
                transferToFragment.goToMainLoginDialogFragment();
                Log.d("TAG" ,product.getId().toString() );
            }
            else {
                LOGIN_FROM_COMMENT = true;
                transferToFragment.goToVideoPlayerActivity(product.getFiles().get(0).getFile());
            }
        }
    }
}
