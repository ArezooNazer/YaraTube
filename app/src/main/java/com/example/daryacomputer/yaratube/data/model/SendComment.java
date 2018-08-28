package com.example.daryacomputer.yaratube.data.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SendComment {

    @SerializedName("error")
    @Expose
    private Integer error;

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

}