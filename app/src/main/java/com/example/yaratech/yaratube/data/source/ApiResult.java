package com.example.yaratech.yaratube.data.source;

public interface ApiResult<T> {
    void onSuccess(T result);
    void onError(String message);

}
