package com.example.yaratech.yaratube.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;

import com.example.yaratech.yaratube.data.entity.User;

@Dao
public interface DeleteDao {
    @Delete
    void deleteUser(User user);

}
