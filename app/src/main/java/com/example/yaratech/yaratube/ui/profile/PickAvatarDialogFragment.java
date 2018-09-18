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
    private String imageFilePath = "";
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

        galleryBut = view.findViewById(R.id.galleryBut);
        cameraBut = view.findViewById(R.id.cameraBut);

        galleryBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) !=
                        PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, GALLERY_PERMISSION);
                } else {
                    takePhotoFromGallery();
                }
            }
        });

        cameraBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) !=
                        PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.CAMERA}, CAMERA_PERMISSION);
                } else {
                    takePhotoFromCamera();
                }
            }
        });

        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getContext(), "Thanks for granting Permission", Toast.LENGTH_SHORT).show();
            }
        }
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
        if (cameraIntent.resolveActivity(getActivity().getPackageManager()) != null) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                openCameraIntent();
            else
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == GALLERY_REQUEST) {

                Uri selectedImage = data.getData();
                cropImage(selectedImage);

            } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {

                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                Uri selectedImage = result.getUri();
                Log.d("avatar", "cropped called" + selectedImage.getPath());
                mListener.photoSelectedListener(selectedImage.getPath());
                getDialog().dismiss();

            } else if (requestCode == CAMERA_REQUEST) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){

                    cropImage(Uri.fromFile(new File(imageFilePath)));
//                    mListener.photoSelectedListener(imageFilePath);
                    Log.d("avatar", "camera plus called" + imageFilePath);
                }else {
                    Uri selectedImage = data.getData();
                    Log.d("avatar", "camera called" + selectedImage.getPath());
                    cropImage(selectedImage);
                }
            }

            Log.d("result", "onActivityResult() called with: requestCode = [" + requestCode + "], resultCode = [" + resultCode + "], Uri = [" + data.getData() + "]");

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

    private File createImageFile() throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "IMG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        imageFilePath = image.getAbsolutePath();

        return image;
    }

    private void openCameraIntent() {
        Intent pictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (pictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {

            File photoFile = null;
            try {
                photoFile = createImageFile();
            }
            catch (IOException e) {
                e.printStackTrace();
                return;
            }
            Uri photoURI = FileProvider.getUriForFile(
                    getContext(),
                    getContext().getPackageName() + ".provider",
                    photoFile);;
            pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            startActivityForResult(pictureIntent, CAMERA_REQUEST);
        }
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
