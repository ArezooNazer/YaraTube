package com.example.daryacomputer.yaratube.home.homePage;

import com.example.daryacomputer.yaratube.data.model.Headeritem;
import com.example.daryacomputer.yaratube.data.model.Homeitem;
import com.example.daryacomputer.yaratube.data.model.Store;

import java.util.List;

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
