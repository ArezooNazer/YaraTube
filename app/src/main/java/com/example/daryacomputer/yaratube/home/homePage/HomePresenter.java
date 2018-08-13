package com.example.daryacomputer.yaratube.home.homePage;

import com.example.daryacomputer.yaratube.data.model.Homeitem;
import com.example.daryacomputer.yaratube.data.model.Store;
import com.example.daryacomputer.yaratube.data.source.ApiResult;
import com.example.daryacomputer.yaratube.data.source.HomeRepository;

import java.util.List;

public class HomePresenter implements HomeContract.Presenter {

    private HomeRepository homeRepository;
    private HomeContract.View mView;

    public HomePresenter( HomeContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void getHomeItems() {
        homeRepository = new HomeRepository();
        homeRepository.getHomeItems(new ApiResult<Store>() {

            @Override
            public void onSuccess(Store result) {
                mView.showHomeItemList(result.getHomeitem());
                mView.showHomeHeader(result.getHeaderitem());

            }

            @Override
            public void onFailure() {
                mView.ShowMassage("Error: can not show HomeItem list");
            }
        });

    }
}

