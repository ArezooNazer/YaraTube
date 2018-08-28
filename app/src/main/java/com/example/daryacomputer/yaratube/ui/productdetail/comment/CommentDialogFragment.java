package com.example.daryacomputer.yaratube.ui.productdetail.comment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.example.daryacomputer.yaratube.R;


public class CommentDialogFragment extends DialogFragment {

    private CommentContract.Presenter mPresenter;

    public CommentDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new CommentPresenter((CommentContract.View) this);

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

        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.round_border_white);
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

        sendCommentBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.sendComment("s" , 3, commentET.getText().toString());
            }
        });

    }
}
