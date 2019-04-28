package com.ankur.mytaxi.di.components;


import com.ankur.mytaxi.di.modules.ActivityFragmentModule;
import com.ankur.mytaxi.di.modules.NetworkModule;
import com.ankur.mytaxi.di.scopes.ActivityScope;
import com.ankur.mytaxi.ui.map.VehicleMapFragment;
import com.ankur.mytaxi.ui.vehiclelist.MainActivity;

import dagger.Subcomponent;

@Subcomponent(modules = {ActivityFragmentModule.class, NetworkModule.class})
@ActivityScope
public interface ActivityFragmentComponent {
    void inject(MainActivity activity);
    void inject(VehicleMapFragment fragment);
}
