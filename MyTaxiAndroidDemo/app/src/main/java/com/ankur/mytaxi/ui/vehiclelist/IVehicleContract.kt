package com.ankur.mytaxi.ui.vehiclelist

import com.ankur.mytaxi.ui.base.IBaseView
import com.ankur.mytaxi.viewmodel.PoiList

interface IVehicleContract {

    interface VehicleListView : IBaseView {
        fun showVehicle(poiList: List<PoiList>)
    }


}
