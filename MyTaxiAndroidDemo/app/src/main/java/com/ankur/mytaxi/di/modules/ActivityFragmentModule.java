package com.ankur.mytaxi.di.modules;

import android.content.Context;
import android.location.Geocoder;

import com.ankur.mytaxi.di.qualifiers.ActivityContext;
import com.ankur.mytaxi.di.qualifiers.AppContext;
import com.ankur.mytaxi.di.scopes.ActivityScope;
import com.ankur.mytaxi.ui.adapter.VehicleRecyclerAdapter;
import com.ankur.mytaxi.viewmodel.PoiList;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityFragmentModule {
    private Context context;
    private VehicleRecyclerAdapter.ItemClickListener listener;

    public ActivityFragmentModule(Context context, VehicleRecyclerAdapter.ItemClickListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @Provides
    @ActivityContext
    @ActivityScope
    public Context getContext() {
        return context;

    }

    @Provides
    @ActivityScope
    public VehicleRecyclerAdapter.ItemClickListener getListener() {
        return listener;
    }

    @Provides
    @ActivityScope
    public VehicleRecyclerAdapter getAdapter(ArrayList<PoiList> list, @AppContext Context context,
                                             VehicleRecyclerAdapter.ItemClickListener listener) {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        return new VehicleRecyclerAdapter(list, geocoder, listener);
    }

    @Provides
    @ActivityScope
    public ArrayList<PoiList> getEmptyInitList() {
        return new ArrayList<PoiList>();
    }

}
