package com.ankur.mytaxi.ui.map

import com.ankur.mytaxi.ui.base.BasePresenter
import com.ankur.mytaxi.util.AppUtils
import com.ankur.mytaxi.viewmodel.PoiList
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import javax.inject.Inject

class MapPresenter @Inject constructor() : BasePresenter<IVehicleMapContract.IVehicleMapView>() {

    fun createLatLngBounds() {
        val builder = LatLngBounds.Builder()
        builder.include(LatLng(AppUtils.P1_LAT, AppUtils.P1_LONG))
        builder.include(LatLng(AppUtils.P2_LAT, AppUtils.P2_LONG))

        mView!!.setBounds(builder.build())
    }

    fun createMapMarkers(poiList: PoiList, direction: String, bitmapDescriptor: BitmapDescriptor?) {
        val lat = poiList.coordinate.latitude
        val lng = poiList.coordinate.longitude

        val carLocation = LatLng(lat, lng)
        val markerOption = MarkerOptions().position(carLocation).title(poiList.fleetType).
                snippet(direction).icon(bitmapDescriptor)
        mView!!.setMarkerOption(markerOption, poiList)
    }
}