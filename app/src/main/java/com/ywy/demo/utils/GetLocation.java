package com.ywy.demo.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class GetLocation {

    private String TAG = GetLocation.class.getSimpleName();
    private Context mContext;
    private static GetLocation mLocation;
    private String locationProvider;
    private Location mLocationContent;
    private GetLocationListener mGetLocationListener;
    private LocationManager mLocationManager;

    private GetLocation() {
    }

    public static synchronized GetLocation getInstance() {
        if (mLocation == null) {
            mLocation = new GetLocation();
        }
        return mLocation;
    }

    @SuppressLint("MissingPermission")
    public Location getLocation(Context context) {
        mLocationManager = (LocationManager) context.getSystemService(context.getApplicationContext().LOCATION_SERVICE);
        List<String> providers = mLocationManager.getProviders(true);
        if (providers.contains(LocationManager.GPS_PROVIDER)) {
            locationProvider = LocationManager.GPS_PROVIDER;
        } else {
            locationProvider = LocationManager.NETWORK_PROVIDER;
        }
        mLocationContent = mLocationManager.getLastKnownLocation(locationProvider);
        Log.d(TAG, "upLoadLocation location = " + mLocationContent);
        return mLocationContent;
    }

    LocationListener mListener = new LocationListener() {
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.e(TAG, "onStatusChanged");
        }

        @Override
        public void onProviderEnabled(String provider) {
            Log.e(TAG, "onProviderEnabled");
        }

        @Override
        public void onProviderDisabled(String provider) {
            Log.e(TAG, "onProviderDisabled");
        }

        // 如果位置发生变化，重新显示
        @Override
        public void onLocationChanged(Location location) {
            Log.e(TAG, "onLocationChanged  location : " + location.toString());
            if (mGetLocationListener != null) {
                mGetLocationListener.getLocationData(location);
            }
        }
    };

    public interface GetLocationListener {
        void getLocationData(Location location);
    }

    public void setGetLocationListener(GetLocationListener getLocationListener) {
        mGetLocationListener = getLocationListener;
        if (mGetLocationListener != null) {
            mLocationManager.requestLocationUpdates(locationProvider, 500, 500, mListener);
        }
    }

}
