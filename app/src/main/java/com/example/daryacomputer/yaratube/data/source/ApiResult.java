package com.example.daryacomputer.yaratube.data.source;

public interface ApiResult<T> {
    void onSuccess(T result);
    void onFailure();

}
