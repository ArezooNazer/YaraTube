package com.example.daryacomputer.yaratube.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

import com.example.daryacomputer.yaratube.data.entity.User;

@Dao
public interface InsertDao {


    @Insert
    void saveUserInfo(User userInfo);

    @Update
    void updateUserInfo(User userInfo);



}
