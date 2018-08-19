package com.example.daryacomputer.yaratube;

public interface Tool {

    void ShowMassage(String message);
    void showProgressBar();
    void hideProgressBar();

    public interface NewInstanceOfFragment<F,M>{
        F newInstance(M model);
    }
}
