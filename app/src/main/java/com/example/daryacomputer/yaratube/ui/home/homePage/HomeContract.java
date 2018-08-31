package com.example.daryacomputer.yaratube.ui.home.homePage;

import com.example.daryacomputer.yaratube.util.BaseView;
import com.example.daryacomputer.yaratube.data.model.Store;

public interface HomeContract {

    interface View extends BaseView{
        void showHomeItemList(Store store);
    }

    interface Presenter{
        void getHomeItems();
    }

}
