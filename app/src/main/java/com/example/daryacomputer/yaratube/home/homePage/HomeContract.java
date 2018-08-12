package com.example.daryacomputer.yaratube.home.homePage;

import com.example.daryacomputer.yaratube.data.model.HomeItem;

import java.util.List;

public interface HomeContract {

    interface View{
        void showProductList(List<HomeItem> homeItemList);
        void ShowMassage(String massage);
    }

    interface Presenter{
        void getHomeItems();
    }
}
