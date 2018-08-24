package com.example.daryacomputer.yaratube;

import com.example.daryacomputer.yaratube.data.YaraDatabase;
import com.example.daryacomputer.yaratube.data.entity.Token;

public class DatabaseUtil {

    static String token;
    public static void addToken(final YaraDatabase db, final Token token){

        new Thread(new Runnable() {
            @Override
            public void run() {
                db.insertDao().saveToken(token);
            }
        }).start();

    }

}
