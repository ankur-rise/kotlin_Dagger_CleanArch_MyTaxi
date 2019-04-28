
package com.ankur.mytaxi.viewmodel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VehicleModel {

    @SerializedName("poiList")
    @Expose
    private List<PoiList> poiList = null;

    public List<PoiList> getPoiList() {
        return poiList;
    }

    public void setPoiList(List<PoiList> poiList) {
        this.poiList = poiList;
    }

}
