package com.example.yaratech.yaratube.ui.productdetail;

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
import com.example.yaratech.yaratube.MainActivity;
import com.example.yaratech.yaratube.R;
import com.example.yaratech.yaratube.util.TransferToFragment;
import com.example.yaratech.yaratube.data.model.Comment;
import com.example.yaratech.yaratube.data.model.Product;
import com.example.yaratech.yaratube.data.source.LoginRepository;
import com.example.yaratech.yaratube.ui.productdetail.comment.CommentAdapter;
import com.example.yaratech.yaratube.ui.productdetail.comment.CommentContract;
import com.example.yaratech.yaratube.ui.productdetail.comment.CommentPresenter;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailFragment extends Fragment implements CommentContract.View, ProductDetailContract.View {

    final public static String PRODUCT_DETAIL_FRAGMENT = ProductDetailFragment.class.getSimpleName();
    final public static String PRODUCT = "product";
    final public static String TITLE = "categoryTitle";
    public static boolean LOGIN_FROM_COMMENT = false;

    private ProductDetailContract.Presenter detailPresenter;
    private List<Comment> commentList = new ArrayList<>();
    private TransferToFragment transferToFragment;
    private ImageView playBut, productDetailImage;
    private CommentContract.Presenter mPresenter;
    private CommentAdapter commentAdapter;
    private ProgressBar progressBar;
    private static String categoryTitle;
    private Product product;
    private Button retryBut;

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
        this.categoryTitle = arg.getString(TITLE);

        commentAdapter = new CommentAdapter(commentList);
        mPresenter = new CommentPresenter(this);
        detailPresenter = new ProductDetailPresenter(this);
        getActivity().setTitle(getString(R.string.productDetail));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prodcut_detail, container, false);

        progressBar = view.findViewById(R.id.detailProgressBar);
        retryBut = view.findViewById(R.id.retryButton);
        retryBut.setVisibility(View.GONE);
        retryBut.bringToFront(); // for on click works on android 4.3!!!!

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter.getCommentList(product);
        detailPresenter.getProductDetail(product);
        //TODO: this must be called in presenter
        setProductDetailData(view);

        Button sendCommentButton = view.findViewById(R.id.sendCommentButton);
        sendCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (LoginRepository.isLogin()) {
                    transferToFragment.goToCommentDialogFragment(product.getId());
                    Log.d("TAG", product.getId().toString());
                } else {
                    LOGIN_FROM_COMMENT = true;
                    transferToFragment.goToMainLoginDialogFragment();
                }
            }
        });

        playBut = view.findViewById(R.id.productDetailIvIcon);
        productDetailImage = view.findViewById(R.id.productDetailIv);
        videoPlayerListener();


    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(categoryTitle.equals(""))
            getActivity().setTitle(R.string.app_name);
        else
            getActivity().setTitle(categoryTitle);
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


    public static ProductDetailFragment newInstance(Product product , String categoryTitle) {

        Bundle arg = new Bundle();
        arg.putParcelable(PRODUCT, (Parcelable) product);
        arg.putString(TITLE, categoryTitle);

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

    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
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

    @Override
    public void enableExoPlayer() {
        playBut.setClickable(true);
        productDetailImage.setClickable(true);
        retryBut.setVisibility(View.GONE);
    }

    @Override
    public void disableExoPlayer() {
        playBut.setClickable(false);
        productDetailImage.setClickable(false);
        retryBut.setVisibility(View.VISIBLE);
        retryBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.getCommentList(product);
                detailPresenter.getProductDetail(product);
            }
        });
    }


    public void videoPlayerListener() {

//        ctrl+shift+a : anonymous -> create inner class
        playBut.setOnClickListener(new MyOnClickListener());
        productDetailImage.setOnClickListener(new MyOnClickListener());

    }

    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            if (!LoginRepository.isLogin()) {
                transferToFragment.goToMainLoginDialogFragment();
                Log.d("TAG", product.getId().toString());
            } else {
                LOGIN_FROM_COMMENT = true;
                transferToFragment.goToVideoPlayerActivity(product.getFiles().get(0).getFile());
            }
        }
    }
}
