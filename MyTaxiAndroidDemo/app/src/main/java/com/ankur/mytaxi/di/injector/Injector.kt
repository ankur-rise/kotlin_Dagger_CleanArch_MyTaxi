package com.ankur.mytaxi.di.injector

import android.content.Context
import com.ankur.mytaxi.MyTaxiApplication
import com.ankur.mytaxi.di.components.ActivityFragmentComponent
import com.ankur.mytaxi.di.modules.ActivityFragmentModule
import com.ankur.mytaxi.ui.adapter.VehicleRecyclerAdapter

class Injector private constructor() {
    private var mActivityComponent: ActivityFragmentComponent? = null

    companion object {
        val instance = Injector()
    }

    fun getActivityComponent(context: Context, listener:VehicleRecyclerAdapter.ItemClickListener?): ActivityFragmentComponent? {
        if (mActivityComponent == null) {
            mActivityComponent = MyTaxiApplication.getInstance().appComponent.
                    plusActivityFragmentModule(ActivityFragmentModule(context, listener))
        }
        return mActivityComponent
    }


}