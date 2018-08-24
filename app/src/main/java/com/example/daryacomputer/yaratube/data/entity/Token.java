package com.example.daryacomputer.yaratube.data.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.example.daryacomputer.yaratube.data.model.Register;

@Entity
public class Token {

    @PrimaryKey(autoGenerate = true)
    int id;
    String token;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
