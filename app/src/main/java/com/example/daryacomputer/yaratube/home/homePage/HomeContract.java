package com.example.daryacomputer.yaratube.home.homePage;

import com.example.daryacomputer.yaratube.data.model.Store;

public interface HomeContract {

    interface View{
        void showHomeItemList(Store store);
        void ShowMassage(String massage);

        void showProgressBar();
        void hideProgressBar();
    }

    interface Presenter{
        void getHomeItems();
    }
}
