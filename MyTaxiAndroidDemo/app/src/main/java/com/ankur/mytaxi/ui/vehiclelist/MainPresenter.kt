package com.ankur.mytaxi.ui.vehiclelist


import com.ankur.mytaxi.interactor.IBaseInteractor
import com.ankur.mytaxi.interactor.IVehicleInteractor
import com.ankur.mytaxi.ui.base.BasePresenter
import com.ankur.mytaxi.viewmodel.PoiList
import com.ankur.mytaxi.viewmodel.VehicleModel
import javax.inject.Inject

class MainPresenter @Inject constructor(var vehicleInterctor: IVehicleInteractor) :
        BasePresenter<IVehicleContract.VehicleListView>() {

    fun getVehicle() {
        mView?.showProgress()
        vehicleInterctor.getVehicles(object : IBaseInteractor.INetworkListener<VehicleModel> {
            override fun onSuccess(result: VehicleModel?) {
                mView?.showVehicle(result!!.poiList as List<PoiList>)
                mView?.hideProgress()
            }

            override fun onError(error: String?) {
                mView?.hideProgress()
            }

        })

    }

}
