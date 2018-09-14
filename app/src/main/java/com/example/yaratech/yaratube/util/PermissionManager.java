package com.example.yaratech.yaratube.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public class PermissionManager {

    public PermissionManager(){}

    private static final String[] PERMISSIONS = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private static final int REQUEST_CODE = 101;
    //define a value to handle the permission callback, in onRequestPermissionsResult():
    public final static int REQUEST_CODE_READ_SMS = 1;

    public static  boolean userHasPermission(Context context){
        int permissionCheck = ContextCompat.checkSelfPermission(context, PERMISSIONS[0]);
        int permissionCheck2 = ContextCompat.checkSelfPermission(context, PERMISSIONS[1]);
        return permissionCheck == PackageManager.PERMISSION_GRANTED &&
                permissionCheck2 == PackageManager.PERMISSION_GRANTED;
    }

    public static   void requestPermission(Activity activity){
        ActivityCompat.requestPermissions(activity, PERMISSIONS, REQUEST_CODE);
    }




    public static void requestPermissionReadSms(Activity activity){
        ActivityCompat.requestPermissions(activity, new String[]{android.Manifest.permission.RECEIVE_SMS},REQUEST_CODE_READ_SMS);
    }
}
