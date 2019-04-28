package com.ankur.mytaxi.network

import com.ankur.mytaxi.viewmodel.VehicleModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface Apis {
    @GET(".")
    fun getVehicle(@QueryMap geoPosition:Map<String, Double>): Call<VehicleModel>
}