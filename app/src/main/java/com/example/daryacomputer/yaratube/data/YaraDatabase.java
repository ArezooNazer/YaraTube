package com.example.daryacomputer.yaratube.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.daryacomputer.yaratube.data.dao.InsertDao;
import com.example.daryacomputer.yaratube.data.dao.SelectDao;
import com.example.daryacomputer.yaratube.data.entity.User;

@Database(entities = {User.class} , version = 2)
public abstract class YaraDatabase extends RoomDatabase{

    public abstract InsertDao insertDao();
    public abstract SelectDao selectDao();

}
