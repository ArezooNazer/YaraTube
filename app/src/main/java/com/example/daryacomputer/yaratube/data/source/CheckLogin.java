package com.example.daryacomputer.yaratube.data.source;

import static com.example.daryacomputer.yaratube.MainActivity.yaraDatabase;

public class CheckLogin {

    public static boolean isLogin(){
        if(yaraDatabase.selectDao().selectToken() != null)
            return true;
        else
            return false;
    }
}
