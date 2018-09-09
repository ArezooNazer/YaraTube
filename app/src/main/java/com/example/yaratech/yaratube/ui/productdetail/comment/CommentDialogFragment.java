package com.example.yaratech.yaratube.ui.productdetail.comment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.yaratech.yaratube.R;
import com.example.yaratech.yaratube.data.YaraDatabase;
import com.example.yaratech.yaratube.data.model.Comment;
import com.example.yaratech.yaratube.data.model.Product;

import java.util.List;

import static com.example.yaratech.yaratube.MainActivity.yaraDatabase;


public class CommentDialogFragment extends DialogFragment implements CommentContract.View{

    private CommentContract.Presenter mPresenter;
    private int productId;

    public CommentDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new CommentPresenter(this);

        productId = getArguments().getInt("productId");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);

        // request a window without the title
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.round_border_white);
        View view = inflater.inflate(R.layout.fragment_comment_dialog, container, false);

        Button button = view.findViewById(R.id.dialogDismissBut);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final EditText commentET = view.findViewById(R.id.commentET);
        Button sendCommentBut = view.findViewById(R.id.sendCommentButton);
        RatingBar ratingBar = view.findViewById(R.id.ratingBar);
        final String token = yaraDatabase.selectDao().selectToken();
        final int score = (int) ratingBar.getRating();

        sendCommentBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.sendComment("" , score, commentET.getText().toString(), productId,"Token " + token);
                Log.d("TAG" ,String.valueOf(productId) );

            }
        });

    }

    @Override
    public void showCommentList(List<Comment> commentList) {

    }

    @Override
    public void commentIsSuccessfullySent() {
        getDialog().dismiss();
    }

    public void ShowMassage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgressBar(){
    }


    @Override
    public void hideProgressBar() {

    }

    public static CommentDialogFragment newInstance(int productId){

        Bundle arg = new Bundle();
        arg.putInt("productId" , productId);

        CommentDialogFragment commentDialogFragment = new CommentDialogFragment();
        commentDialogFragment.setArguments(arg);
        return commentDialogFragment;
    }
}
