package com.example.daryacomputer.yaratube.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import com.example.daryacomputer.yaratube.data.entity.Token;

@Dao
public interface InsertDao {

    @Insert
    void saveToken(Token token);
}
