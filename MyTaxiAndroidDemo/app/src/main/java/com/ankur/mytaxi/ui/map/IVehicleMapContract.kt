package com.ankur.mytaxi.ui.map

import com.ankur.mytaxi.ui.base.IBaseView
import com.ankur.mytaxi.viewmodel.PoiList
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions

interface IVehicleMapContract {
    interface IVehicleMapView:IBaseView{
        fun setBounds(bound:LatLngBounds)
        fun setMarkerOption(marker: MarkerOptions, poiList: PoiList)
    }
}