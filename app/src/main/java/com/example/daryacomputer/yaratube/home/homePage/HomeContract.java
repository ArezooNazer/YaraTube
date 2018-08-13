package com.example.daryacomputer.yaratube.home.homePage;

import com.example.daryacomputer.yaratube.data.model.Homeitem;

import java.util.List;

public interface HomeContract {

    interface View{
        void showHomeItemList(List<Homeitem> homeItemList);
        void ShowMassage(String massage);
    }

    interface Presenter{
        void getHomeItems();
    }
}