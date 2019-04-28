package com.ankur.mytaxi;

import com.ankur.mytaxi.ui.map.IVehicleMapContract;
import com.ankur.mytaxi.ui.map.MapPresenter;
import com.ankur.mytaxi.viewmodel.Coordinate;
import com.ankur.mytaxi.viewmodel.PoiList;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class MapPresenterTest {
    private MapPresenter presenter;
    @Mock
    private IVehicleMapContract.IVehicleMapView view;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        presenter = new MapPresenter();
        presenter.bindView(view);
    }

    @Test
    public void testLatLongBounds(){

        presenter.createLatLngBounds();

        verify(view).setBounds(any(LatLngBounds.class));

    }

    @Test
    public void testMarkerOption() {
        PoiList poiList = new PoiList();
        Coordinate c = new Coordinate();
        c.setLatitude(53.14);
        c.setLongitude(33.1);
        poiList.setCoordinate(c);
        poiList.setFleetType("Taxi");

        presenter.createMapMarkers(poiList, "North-East", null);

        verify(view).setMarkerOption(any(MarkerOptions.class), any(PoiList.class));

    }

}
