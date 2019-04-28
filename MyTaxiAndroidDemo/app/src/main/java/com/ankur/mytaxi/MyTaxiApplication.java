package com.ankur.mytaxi;

import android.app.Application;

import com.ankur.mytaxi.di.components.ApplicationComponent;
import com.ankur.mytaxi.di.components.DaggerApplicationComponent;
import com.ankur.mytaxi.di.modules.ApplicationModule;
import com.ankur.mytaxi.di.modules.RetrofitModule;

public class MyTaxiApplication extends Application {
    private static MyTaxiApplication mApplication;
    private ApplicationComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }

    public static MyTaxiApplication getInstance() {
        return mApplication;
    }

    public ApplicationComponent getAppComponent() {
        if (mAppComponent == null) {
            mAppComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this))
                    .retrofitModule(new RetrofitModule()).build();
        }
        return mAppComponent;
    }
}
