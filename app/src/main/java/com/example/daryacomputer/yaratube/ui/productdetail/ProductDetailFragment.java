package com.example.daryacomputer.yaratube.ui.productdetail;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.daryacomputer.yaratube.R;
import com.example.daryacomputer.yaratube.Tool;
import com.example.daryacomputer.yaratube.data.model.Comment;
import com.example.daryacomputer.yaratube.data.model.Product;
import com.example.daryacomputer.yaratube.ui.productdetail.comment.ProductDetailCommentAdapter;
import com.example.daryacomputer.yaratube.ui.productdetail.comment.ProductDetailCommentContract;
import com.example.daryacomputer.yaratube.ui.productdetail.comment.ProductDetailCommentPresenter;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailFragment extends Fragment implements ProductDetailCommentContract.View{

    final public static String PRODUCT_DETAIL_FRAGMENT = ProductDetailFragment.class.getSimpleName();
    final public static String PRODUCT = "product";
    private ProductDetailCommentContract.Presenter mPresenter;
    private ProductDetailCommentAdapter productDetailCommentAdapter;
    private List<Comment> commentList = new ArrayList<>();
    private Product product;
    ProgressBar progressBar ;

    public ProductDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arg = getArguments();
        if (arg == null) return;
        setProduct((Product) arg.getParcelable(PRODUCT));

        productDetailCommentAdapter = new ProductDetailCommentAdapter(commentList);
        mPresenter = new ProductDetailCommentPresenter((ProductDetailCommentContract.View) this);
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

        setProductDetailData(view);

        mPresenter.getCommentList(product);
    }

    public void setProductDetailData(View view) {

        RecyclerView productDetailComment = view.findViewById(R.id.productDetailRvComment);
        productDetailComment.setLayoutManager(new LinearLayoutManager(getContext()));
        productDetailComment.setAdapter(productDetailCommentAdapter);

        ImageView productDetailImageView = view.findViewById(R.id.productDetailIv);
        TextView productDetailTitle = view.findViewById(R.id.productDetailTvTitle);
        TextView productDetailDesc = view.findViewById(R.id.ProductDetailTvDesc);

        String url = product.getAvatarUrl();
        Glide.with(view.getContext()).load(url).into(productDetailImageView);

        if(product.getName() != null)
          productDetailTitle.setText(product.getName());
        if (product.getShortDescription() != null)
          productDetailDesc.setText(product.getShortDescription());
    }


    public static ProductDetailFragment newInstance(Product product) {

        Bundle arg = new Bundle();
        arg.putParcelable(PRODUCT, product);

        ProductDetailFragment productDetailFragment = new ProductDetailFragment();
        productDetailFragment.setArguments(arg);
        return productDetailFragment;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public void showProductDetailCommentList(List<Comment> commentList) {
        productDetailCommentAdapter.updateData(commentList);
    }

    public void ShowMassage(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }


    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

}