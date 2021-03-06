package com.example.yaratech.yaratube.data.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//this model is response for sending login activation code for registration
public class Register {

    @SerializedName("fino_token")
    @Expose
    private String finoToken;
    @SerializedName("nickname")
    @Expose
    private String nickname;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("error")
    @Expose
    private Integer error;
    @SerializedName("files_added")
    @Expose
    private List<Object> filesAdded = null;

    public Register() {
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

    public List<Object> getFilesAdded() {
        return filesAdded;
    }

    public void setFilesAdded(List<Object> filesAdded) {
        this.filesAdded = filesAdded;
    }

}
