package com.example.yaratech.yaratube.ui.home.homepage;

import com.example.yaratech.yaratube.data.model.Store;
import com.example.yaratech.yaratube.data.source.ApiResult;
import com.example.yaratech.yaratube.data.source.HomeRepository;


public class HomePresenter implements HomeContract.Presenter {
    private HomeRepository homeRepository;
    private HomeContract.View mView;

    public HomePresenter(HomeContract.View mView) {
        this.mView = mView;
        homeRepository = new HomeRepository();
    }

    @Override
    public void getHomeItems() {

        mView.showProgressBar();
        homeRepository.getHomeItems(new ApiResult<Store>() {

            @Override
            public void onSuccess(Store result) {

                mView.hideProgressBar();
                mView.showHomeItemList(result);
            }

            @Override
            public void onError(String massage) {
                mView.showMessage(massage);
            }

        });

    }
}
