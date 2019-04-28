package com.ankur.mytaxi;

import com.ankur.mytaxi.interactor.IBaseInteractor;
import com.ankur.mytaxi.interactor.IVehicleInteractor;
import com.ankur.mytaxi.ui.vehiclelist.IVehicleContract;
import com.ankur.mytaxi.ui.vehiclelist.MainPresenter;
import com.ankur.mytaxi.viewmodel.PoiList;
import com.ankur.mytaxi.viewmodel.VehicleModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.verify;

public class MainPresenterTest {
    private MainPresenter presenter;
    @Mock
    private IVehicleContract.VehicleListView view;
    @Mock
    private IVehicleInteractor interactor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        presenter = new MainPresenter(interactor);
        presenter.bindView(view);
    }

    @Test
    public void testGetVehicle() {
        VehicleModel model = new VehicleModel();
        model.setPoiList(new ArrayList<PoiList>());
        ArgumentCaptor<IBaseInteractor.INetworkListener> captor = ArgumentCaptor.forClass(IBaseInteractor.INetworkListener.class);

        presenter.getVehicle();
        verify(view).showProgress();
        verify(interactor).getVehicles(captor.capture());
        IBaseInteractor.INetworkListener<VehicleModel> listener  = captor.getValue();
        listener.onSuccess(model);
        verify(view).showVehicle(anyListOf(PoiList.class));
        verify(view).hideProgress();
    }

    @Test
    public void testGetVehicleFailure() {
        String error = "Server not found";
        ArgumentCaptor<IBaseInteractor.INetworkListener> captor = ArgumentCaptor.forClass(IBaseInteractor.INetworkListener.class);

        presenter.getVehicle();
        verify(view).showProgress();
        verify(interactor).getVehicles(captor.capture());
        IBaseInteractor.INetworkListener<VehicleModel> listener  = captor.getValue();
        listener.onError(error);

        verify(view).hideProgress();
    }

}
