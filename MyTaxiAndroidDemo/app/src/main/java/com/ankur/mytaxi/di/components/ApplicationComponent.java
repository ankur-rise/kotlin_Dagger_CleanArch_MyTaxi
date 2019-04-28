package com.ankur.mytaxi.di.components;


import com.ankur.mytaxi.di.modules.ActivityFragmentModule;
import com.ankur.mytaxi.di.modules.ApplicationModule;
import com.ankur.mytaxi.di.modules.RetrofitModule;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {ApplicationModule.class, RetrofitModule.class})
@Singleton
public interface ApplicationComponent {
    ActivityFragmentComponent plusActivityFragmentModule(ActivityFragmentModule module);

}
