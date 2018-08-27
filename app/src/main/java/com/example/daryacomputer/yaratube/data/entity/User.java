package com.example.daryacomputer.yaratube.data.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String finoToken;
    private String nickname;
    private String token;
    private String message;
    private Integer error;


    public User(String finoToken, String nickname, String token, String message, Integer error) {
        this.finoToken = finoToken;
        this.nickname = nickname;
        this.token = token;
        this.message = message;
        this.error = error;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFinoToken() {
        return finoToken;
    }

    public void setFinoToken(String finoToken) {
        this.finoToken = finoToken;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }
}
