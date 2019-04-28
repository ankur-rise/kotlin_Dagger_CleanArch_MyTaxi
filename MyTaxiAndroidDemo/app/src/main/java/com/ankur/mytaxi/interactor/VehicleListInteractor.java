package com.ankur.mytaxi.interactor;

import com.ankur.mytaxi.network.Apis;
import com.ankur.mytaxi.viewmodel.VehicleModel;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VehicleListInteractor implements IVehicleInteractor {
    private Apis apis;

    @Inject
    public VehicleListInteractor(Apis apis) {
        this.apis = apis;
    }

    @Override
    public void getVehicles(final INetworkListener<VehicleModel> listener) {
        Map<String, Double> map = new HashMap<>();
        map.put("p1Lat", 53.694865);
        map.put("p1Lon", 9.757589);
        map.put("p2Lat", 53.394655);
        map.put("p2Lon", 10.099891);
        apis.getVehicle(map).enqueue(new Callback<VehicleModel>() {
            @Override
            public void onResponse(Call<VehicleModel> call, Response<VehicleModel> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    listener.onError(response.message());
                }
            }

            @Override
            public void onFailure(Call<VehicleModel> call, Throwable t) {
                listener.onError(t.getMessage());
            }
        });
    }

}
