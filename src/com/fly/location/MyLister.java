package com.fly.location;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by John on 2016/12/7.
 */
public class MyLister implements LocationListener {
    @Override
    public void onLocationChanged(Location location) {
//        Toast.makeText(LocationDemoActivity.this, location.getLatitude() + ":" + location.getLongitude(), 1000).show();
        System.out.println(location.getLatitude()+":"+location.getLongitude());
//        LocationDemoActivity.this.setTitle(location.getLatitude()+":"+location.getLongitude());
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
