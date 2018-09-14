package com.example.yaratech.yaratube.ui.profile.pickavatar;

import android.app.Dialog;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yaratech.yaratube.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;
import static com.example.yaratech.yaratube.data.source.Constant.TOKEN;

public class PickAvatarDialogFragment extends DialogFragment implements PickAvatarContract.View {

    public static final int GALLERY_REQUEST = 9002;
    public static final int CAMERA_REQUEST = 9003;
    public static String AVATAR_OPTION_DIALOG = PickAvatarDialogFragment.class.getName();
    private ImageView galleryBut, cameraBut;
    private Button closeBut;
    private PickAvatarContract.Presenter mPresenter;

    public PickAvatarDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new PickAvatarPresenter(this);
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
        View view = inflater.inflate(R.layout.fragment_pick_avatar_dialog, container, false);

        galleryBut = view.findViewById(R.id.galleryBut);
        cameraBut = view.findViewById(R.id.cameraBut);
        closeBut = view.findViewById(R.id.dialogDismissBut);

        //TODO: check camera and gallery permissions
        //TODO: provide image cropper!!
        galleryBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePhotoFromGallery();
            }
        });

        cameraBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    takePhotoFromCamera();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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


    private void takePhotoFromCamera() throws IOException {

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File photoFile = createImageFileWith();

        String path = photoFile.getPath();
        Uri cameraUri = Uri.parse(path);

//                FileProvider.getUriForFile(getActivity(),
//                getString(R.string.file_provider_authority),
//                photoFile);

        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, cameraUri);
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
            cameraIntent.setClipData(ClipData.newRawUri("", cameraUri));
            cameraIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        }

        startActivityForResult(cameraIntent, CAMERA_REQUEST);

    }

    public File createImageFileWith() throws IOException {
        final String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        final String imageFileName = "JPEG_" + timestamp;
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "pics");
        storageDir.mkdirs();
        return File.createTempFile(imageFileName, ".jpg", storageDir);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == GALLERY_REQUEST)
                userSelectGallery(data);

            else if (requestCode == CAMERA_REQUEST)
                userSelectCamera(data);
        }
    }

    private void userSelectGallery(Intent data) {

        //the address of the image on the SD card
        Uri selectedImage = data.getData();
        String[] filePathColumn = {MediaStore.Images.Media.DATA};

        android.database.Cursor cursor = getContext().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
        if (cursor == null)
            return;

        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String filePath = cursor.getString(columnIndex);
        cursor.close();

        File file = new File(filePath);
        long imageSize = file.length() /1024;

        if(imageSize > 1000)
            showMessage("حجم عکس بیشتر از 1mb است");
       else {

            RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"), file);
            MultipartBody.Part body = MultipartBody.Part.createFormData("picture", file.getName(), requestFile);
            mPresenter.sendUserAvatarToServer(body, "Token " + TOKEN);
        }


    }

    private void userSelectCamera(Intent data) {
        showMessage("camera selected");
    }


    @Override
    public void dismissDialog() {
        getDialog().dismiss();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }
}
