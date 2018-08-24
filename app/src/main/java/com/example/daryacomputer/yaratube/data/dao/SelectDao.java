package com.example.daryacomputer.yaratube.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.daryacomputer.yaratube.data.entity.Token;

@Dao
public interface SelectDao {

    @Query("SELECT token FROM Token")
    String  selectToken();
}
