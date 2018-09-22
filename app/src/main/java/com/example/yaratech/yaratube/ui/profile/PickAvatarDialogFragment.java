package com.example.yaratech.yaratube.ui.profile;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yaratech.yaratube.R;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;

public class PickAvatarDialogFragment extends DialogFragment {

    public static String AVATAR_OPTION_DIALOG = PickAvatarDialogFragment.class.getName();
    public static final int GALLERY_REQUEST = 9002;
    public static final int CAMERA_REQUEST = 9003;
    public static final int GALLERY_PERMISSION = 9004;
    public static final int CAMERA_PERMISSION = 9005;

    private ImageView galleryBut, cameraBut;
    private String imageFilePath;

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
        this.mListener = listener;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        getDialog().setCanceledOnTouchOutside(false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        galleryBut = view.findViewById(R.id.galleryBut);
        cameraBut = view.findViewById(R.id.cameraBut);

        galleryBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, GALLERY_PERMISSION);
                    else
                        takePhotoFromGallery();
                } else
                    takePhotoFromGallery();
            }
        });

        cameraBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED &&
                            ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.CAMERA}, CAMERA_PERMISSION);
                    else
                        takePhotoFromCamera();

                } else {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case CAMERA_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    takePhotoFromCamera();
                else
                    Log.d("camera", "onRequestPermissionsResult() : permission denied");
                break;
            case GALLERY_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    takePhotoFromGallery();
                else
                    Log.d("gallery", "onRequestPermissionsResult() : permission denied");
                break;
        }
    }

    private void takePhotoFromGallery() {

        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY_REQUEST);
    }


    private void takePhotoFromCamera() {

        Intent pictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (pictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {

            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(
                        getContext(),
                        getContext().getPackageName() + ".provider",
                        photoFile);
                ;
                pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(pictureIntent, CAMERA_REQUEST);
            }
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == GALLERY_REQUEST) {
                cropImage(data.getData());

            } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {

                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                Uri selectedImage = result.getUri();
                Log.d("avatar", "cropped called" + selectedImage.getPath());
                mListener.photoSelectedListener(selectedImage.getPath());
                getDialog().dismiss();


            } else if (requestCode == CAMERA_REQUEST) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    cropImage(Uri.fromFile(new File(imageFilePath)));
                    Log.d("avatar", "camera plus called" + imageFilePath);
                } else {
                    Log.d("avatar", "camera called" + data.getData().getPath());
                    cropImage(data.getData());
                }
            }
        }

    }


    private File createImageFile() throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "IMG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        imageFilePath = image.getAbsolutePath();

        return image;
    }

    public void cropImage(Uri imagePath) {
        CropImage
                .activity(imagePath)
                .setAllowFlipping(true)
                .setAllowRotation(true)
                .setCropShape(CropImageView.CropShape.RECTANGLE)
                .setOutputCompressQuality(50)
                .setFixAspectRatio(true)
                .start(getContext(), this);
    }

}
