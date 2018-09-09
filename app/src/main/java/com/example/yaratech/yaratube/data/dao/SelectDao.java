package com.example.yaratech.yaratube.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.yaratech.yaratube.data.entity.User;
import com.example.yaratech.yaratube.data.model.Register;

@Dao
public interface SelectDao {

    @Query("SELECT * FROM USER")
    User getUserRecord();

    @Query("SELECT token FROM User")
    String selectToken();

    @Query("SELECT phoneNumber FROM User")
    String selectPhoneNumber();

}
