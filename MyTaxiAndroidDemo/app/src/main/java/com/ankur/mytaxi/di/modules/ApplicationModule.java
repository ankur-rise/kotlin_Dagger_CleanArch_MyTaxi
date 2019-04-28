package com.ankur.mytaxi.di.modules;

import android.content.Context;
import android.support.annotation.NonNull;

import com.ankur.mytaxi.di.qualifiers.AppContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private Context context;

    public ApplicationModule(@NonNull Context context) {
        this.context = context;
    }

    @AppContext
    @Provides
    @Singleton
    public Context getAppContext() {
        return context;
    }


}
