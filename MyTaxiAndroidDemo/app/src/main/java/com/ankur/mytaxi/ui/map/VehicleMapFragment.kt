package com.ankur.mytaxi.ui.map

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import com.ankur.mytaxi.R
import com.ankur.mytaxi.di.injector.Injector
import com.ankur.mytaxi.util.AppUtils
import com.ankur.mytaxi.viewmodel.PoiList
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*
import javax.inject.Inject

const val PARAM_VEHICLE_LIST = "com.list.poilist"

class VehicleMapFragment : Fragment(), IVehicleMapContract.IVehicleMapView {
    @BindView(R.id.map_view)
    @JvmField
    var mMapView: MapView? = null

    @Inject
    @JvmField
    internal var mMapPresenter: MapPresenter? = null
    private var mGoogleMap: GoogleMap? = null
    private var mVehicleList: ArrayList<PoiList>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_map, null, false)

        ButterKnife.bind(this, view)
        Injector.instance.getActivityComponent(context!!, null)!!
                .inject(this)

        mMapPresenter!!.bindView(this)
        val extras = this.arguments
        mVehicleList = extras!!.getSerializable(PARAM_VEHICLE_LIST) as ArrayList<PoiList>

        mMapView!!.onCreate(savedInstanceState)

        return view
    }

    override fun onResume() {
        super.onResume()
        mMapView!!.onResume()

        try {
            MapsInitializer.initialize(activity!!.applicationContext)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        mMapView!!.getMapAsync { googleMap ->
            mGoogleMap = googleMap
            if (AppUtils.isNotNullOrEmpty(mVehicleList)) {
                for (poiList in mVehicleList!!) {
                    mMapPresenter!!.createMapMarkers(poiList, AppUtils.getDirection(context, poiList.heading!!),
                            BitmapDescriptorFactory.fromResource(R.drawable.ic_taxi))
                }
            }
            mGoogleMap!!.setOnMapLoadedCallback {
                mMapPresenter!!.createLatLngBounds()
            }
        }

    }

    override fun onPause() {
        super.onPause()
        mMapView!!.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mMapView!!.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mMapView!!.onLowMemory()
    }

    override fun setBounds(bound: LatLngBounds) {
        val cu = CameraUpdateFactory.newLatLngBounds(bound, 20)
        mGoogleMap!!.animateCamera(cu)
    }

    override fun setMarkerOption(markerOptions: MarkerOptions, poiList: PoiList) {
        val marker = mGoogleMap!!.addMarker(markerOptions)
        marker.rotation = poiList.heading!!.toFloat()
    }

    override fun showProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showToast(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
