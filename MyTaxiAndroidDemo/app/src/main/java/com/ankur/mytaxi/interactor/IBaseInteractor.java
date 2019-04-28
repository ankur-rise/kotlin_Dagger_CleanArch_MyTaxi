package com.ankur.mytaxi.interactor;

import com.ankur.mytaxi.viewmodel.VehicleModel;

public interface IBaseInteractor {
    interface INetworkListener<R> {
        void onSuccess(R result);
        void onError(String error);
    }

}
