package com.ankur.mytaxi.interactor;

import com.ankur.mytaxi.viewmodel.VehicleModel;

public interface IVehicleInteractor extends IBaseInteractor {
    void getVehicles(INetworkListener<VehicleModel> listener);
}
