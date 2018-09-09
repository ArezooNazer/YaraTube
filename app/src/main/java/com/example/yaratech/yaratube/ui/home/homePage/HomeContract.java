package com.example.yaratech.yaratube.ui.home.homePage;

import com.example.yaratech.yaratube.util.BaseView;
import com.example.yaratech.yaratube.data.model.Store;

public interface HomeContract {

    interface View extends BaseView{
        void showHomeItemList(Store store);
    }

    interface Presenter{
        void getHomeItems();
    }

}
