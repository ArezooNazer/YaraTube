package com.example.yaratech.yaratube.ui.profile;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.example.yaratech.yaratube.R;

import java.io.File;

import static android.app.Activity.RESULT_OK;

public class PickAvatarDialogFragment extends DialogFragment  {


    public static String AVATAR_OPTION_DIALOG = PickAvatarDialogFragment.class.getName();
    public static final int GALLERY_REQUEST = 9002;
    public static final int CAMERA_REQUEST = 9003;
    public static final int GALLERY_PERMISSION = 9004;
    public static final int CAMERA_PERMISSION = 9005;

    private ImageView galleryBut, cameraBut;
    private Button closeBut;
    //    private PickAvatarContract.Presenter mPresenter;
    private ProfileContract.Listener mListener;

    public PickAvatarDialogFragment() {

    }

    public static PickAvatarDialogFragment newInstance() {
        Bundle bundle = new Bundle();
        PickAvatarDialogFragment pickAvatarDialogFragment = new PickAvatarDialogFragment();
        pickAvatarDialogFragment.setArguments(bundle);
        return pickAvatarDialogFragment;
    }

    public void setPhotoSelectedListener(ProfileContract.Listener listener) {
        mListener = listener;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mPresenter = new PickAvatarPresenter(this);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pick_avatar_dialog, container, false);

        galleryBut = view.findViewById(R.id.galleryBut);
        cameraBut = view.findViewById(R.id.cameraBut);
        closeBut = view.findViewById(R.id.dialogDismissBut);

        //TODO: provide image cropper!!
        galleryBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) !=
                        PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, GALLERY_PERMISSION);
                }
                takePhotoFromGallery();
            }
        });

        cameraBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) !=
                        PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, CAMERA_PERMISSION);
                }

                takePhotoFromCamera();
            }
        });

        closeBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        return view;
    }


    private void takePhotoFromGallery() {

        Intent galleryIntent = new Intent(Intent.ACTION_PICK);
        File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        String picturePath = pictureDirectory.getPath();
        Uri galleryUri = Uri.parse(picturePath);
        galleryIntent.setDataAndType(galleryUri, "image/*");

        startActivityForResult(galleryIntent, GALLERY_REQUEST);
    }


    private void takePhotoFromCamera() {

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == GALLERY_REQUEST) {

                Uri selectedImage = data.getData();
                mListener.photoSelectedListener(createFilePath(selectedImage));
            } else if (requestCode == CAMERA_REQUEST) {

                Uri selectedImage = data.getData();
                mListener.photoSelectedListener(createFilePath(selectedImage));
            }
            getDialog().dismiss();
        }
    }


    private String createFilePath(Uri selectedImage) {

        String[] filePathColumn = {MediaStore.Images.Media.DATA};

        android.database.Cursor cursor = getContext().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String filePath = cursor.getString(columnIndex);
        cursor.close();
        return filePath;
    }

}
