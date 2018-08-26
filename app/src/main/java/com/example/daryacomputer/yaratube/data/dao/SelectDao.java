package com.example.daryacomputer.yaratube.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.daryacomputer.yaratube.data.model.Register;

@Dao
public interface SelectDao {

    @Query("SELECT token FROM User")
    String selectToken();
}
