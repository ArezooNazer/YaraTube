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
        homeRepository = new HomeRepository();
    }

    @Override
    public void getHomeItems() {

        homeRepository.getHomeItems(new ApiResult<Store>() {

            @Override
            public void onSuccess(Store result) {

                mView.showProgressBar();
                mView.showHomeItemList(result);
                mView.hideProgressBar();

            }

            @Override
            public void onError(String massage) {
                mView.ShowMassage(massage);
            }

        });

    }
}

