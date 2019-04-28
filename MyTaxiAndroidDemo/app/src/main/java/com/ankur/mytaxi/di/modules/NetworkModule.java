package com.ankur.mytaxi.di.modules;


import com.ankur.mytaxi.di.scopes.ActivityScope;
import com.ankur.mytaxi.interactor.IVehicleInteractor;
import com.ankur.mytaxi.interactor.VehicleListInteractor;
import com.ankur.mytaxi.network.Apis;

import dagger.Module;
import dagger.Provides;

@Module
public class NetworkModule {
    @Provides
    @ActivityScope
    public IVehicleInteractor getVehicleInteractor(Apis apis) {
        return new VehicleListInteractor(apis);
    }

}
