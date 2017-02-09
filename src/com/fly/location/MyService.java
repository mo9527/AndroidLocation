package com.fly.location;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import com.baidu.location.*;
import com.fly.location.baidu.MyLocationListener;

import java.util.Iterator;

/**
 * Created by John on 2016/12/7.
 */
public class MyService extends Service {
    private static final String TAG = "MyService";
    private static LocationClient mLocationClient = null;
    Thread thread;
    private Context context;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        context = getApplicationContext();

        Log.e("服务已启动", "服务已启动+++++++++++++++");

        try {
            thread = new Thread(new startLockLocation());
            thread.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    class startLockLocation implements Runnable{
        @Override
        public void run() {
            Looper.prepare();
            BDLocationListener myListener = new MyLocationListener();
            mLocationClient = new LocationClient(context);
            mLocationClient.registerLocationListener(myListener);    //注册监听函数
            initLocation();
            if (!mLocationClient.isStarted()){
                mLocationClient.start();
                mLocationClient.requestLocation();
            }
            Looper.loop();
        }
    }


    @Override
    public void onDestroy() {
        thread.destroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){

        return START_STICKY;
    }


    public static void initLocation(LocationClient... locationClients){
        if (locationClients != null && locationClients.length != 0){
            mLocationClient = locationClients[0];
        }
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span=1000*10;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        option.setProdName("weiwt");
        mLocationClient.setLocOption(option);
    }

}


