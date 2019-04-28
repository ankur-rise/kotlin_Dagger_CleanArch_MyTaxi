package com.ankur.mytaxi.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.ankur.mytaxi.R;
import com.ankur.mytaxi.viewmodel.PoiList;

import java.util.ArrayList;

public class AppUtils {

    public static final double P1_LAT = 53.694865;
    public static final double P1_LONG = 9.757589;

    public static final double P2_LAT = 53.394655;
    public static final double P2_LONG = 10.099891;

    public static String getDirection(Context context, double heading) {
        String[] arr = context.getResources().getStringArray(R.array.direction);
        int index = (int) heading / 45;
        return "Heading to "+arr[index];
    }

    public static boolean isNetworkAvailable(final Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static boolean isNotNullOrEmpty(ArrayList<PoiList> mVehicleList) {
        return mVehicleList!=null && !mVehicleList.isEmpty();
    }
}
