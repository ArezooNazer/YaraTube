package com.example.yaratech.yaratube.data.source;

import android.os.Build;

import static com.example.yaratech.yaratube.MainActivity.yaraDatabase;

public class Constant {
    public static String BASE_URL = "https://api.vasapi.click/";

    public static String DEVICE_MODEL = Build.MODEL;

    public static String DEVICE_OS = Build.VERSION.RELEASE;

    public static int LIMIT = 10;

    public static String TOKEN =  yaraDatabase.selectDao().selectToken();

}
