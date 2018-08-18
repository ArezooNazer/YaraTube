package com.example.daryacomputer.yaratube.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static com.example.daryacomputer.yaratube.data.source.Constant.BASE_URL;

public class Category implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("is_default")
    @Expose
    private Boolean isDefault;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("avatar")
    @Expose
    private Object avatar;
    @SerializedName("position")
    @Expose
    private Integer position;
    @SerializedName("is_enable")
    @Expose
    private Boolean isEnable;
    @SerializedName("is_visible")
    @Expose
    private Boolean isVisible;
    @SerializedName("parent")
    @Expose
    private Integer parent;
    @SerializedName("childs")
    @Expose
    private List<Object> childs = null;

    protected Category(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        byte tmpIsDefault = in.readByte();
        isDefault = tmpIsDefault == 0 ? null : tmpIsDefault == 1;
        title = in.readString();
        if (in.readByte() == 0) {
            position = null;
        } else {
            position = in.readInt();
        }
        byte tmpIsEnable = in.readByte();
        isEnable = tmpIsEnable == 0 ? null : tmpIsEnable == 1;
        byte tmpIsVisible = in.readByte();
        isVisible = tmpIsVisible == 0 ? null : tmpIsVisible == 1;
        if (in.readByte() == 0) {
            parent = null;
        } else {
            parent = in.readInt();
        }
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getAvatar() {
        return avatar;
    }
    public String getAvatarUrl(){
        return BASE_URL + getAvatar();
    }

    public void setAvatar(Object avatar) {
        this.avatar = avatar;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Boolean getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
    }

    public Boolean getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(Boolean isVisible) {
        this.isVisible = isVisible;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public List<Object> getChilds() {
        return childs;
    }

    public void setChilds(List<Object> childs) {
        this.childs = childs;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
        parcel.writeByte((byte) (isDefault == null ? 0 : isDefault ? 1 : 2));
        parcel.writeString(title);
        if (position == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(position);
        }
        parcel.writeByte((byte) (isEnable == null ? 0 : isEnable ? 1 : 2));
        parcel.writeByte((byte) (isVisible == null ? 0 : isVisible ? 1 : 2));
        if (parent == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(parent);
        }
    }
}