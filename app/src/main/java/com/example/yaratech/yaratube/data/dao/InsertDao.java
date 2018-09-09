package com.example.yaratech.yaratube.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

import com.example.yaratech.yaratube.data.entity.User;

@Dao
public interface InsertDao {


    @Insert
    void saveUserInfo(User userInfo);

    @Update
    void updateUserInfo(User userInfo);



}
