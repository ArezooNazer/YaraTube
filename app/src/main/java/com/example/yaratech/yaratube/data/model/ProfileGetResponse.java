package com.example.yaratech.yaratube.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProfileGetResponse {

    @SerializedName("friends")
    @Expose
    private List<Object> friends = null;
    @SerializedName("date_of_birth")
    @Expose
    private Object dateOfBirth;
    @SerializedName("magic_credit")
    @Expose
    private MagicCredit magicCredit;
    @SerializedName("mobile")
    @Expose
    private Object mobile;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nickname")
    @Expose
    private String nickname;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("gender")
    @Expose
    private Object gender;
    @SerializedName("credit")
    @Expose
    private Credit credit;

    public List<Object> getFriends() {
        return friends;
    }

    public void setFriends(List<Object> friends) {
        this.friends = friends;
    }

    public Object getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Object dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public MagicCredit getMagicCredit() {
        return magicCredit;
    }

    public void setMagicCredit(MagicCredit magicCredit) {
        this.magicCredit = magicCredit;
    }

    public Object getMobile() {
        return mobile;
    }

    public void setMobile(Object mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Object getGender() {
        return gender;
    }

    public void setGender(Object gender) {
        this.gender = gender;
    }

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

}
