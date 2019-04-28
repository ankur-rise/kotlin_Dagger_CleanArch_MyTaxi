package com.ankur.mytaxi.network;

import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.List;

public class GeocoderHelper extends AsyncTask<Double, Void, String> {
    private Geocoder geocoder;
    private GeocoderAddressListener listener;

    public GeocoderHelper(Geocoder geocoder, GeocoderAddressListener listener) {
        this.geocoder = geocoder;
        this.listener = listener;
    }

    @Override
    protected String doInBackground(Double... coordinates) {

        String address = "";
        List<Address> addresses;

        try {
            addresses = geocoder.getFromLocation(coordinates[0], coordinates[1], 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            address = addresses.get(0).getAddressLine(0);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return address;
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected void onPostExecute(String address) {
        super.onPostExecute(address);
        if(!isCancelled()){
            listener.onSuccess(address);
        }
    }

    public interface GeocoderAddressListener {
        void onSuccess(String address);
    }
}
