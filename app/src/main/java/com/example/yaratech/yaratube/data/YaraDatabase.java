package com.example.yaratech.yaratube.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.yaratech.yaratube.data.dao.InsertDao;
import com.example.yaratech.yaratube.data.dao.SelectDao;
import com.example.yaratech.yaratube.data.entity.User;

@Database(entities = {User.class}, version = 2, exportSchema = false)
public abstract class YaraDatabase extends RoomDatabase{

    public abstract InsertDao insertDao();
    public abstract SelectDao selectDao();

}
