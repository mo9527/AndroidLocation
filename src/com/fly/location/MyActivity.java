package com.fly.location;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.baidu.location.BDLocation;
import com.baidu.location.BDNotifyListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.fly.location.baidu.MyLocationListener;

import javax.net.ssl.ManagerFactoryParameters;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    private Button startBtn;
    private Button stopBtn;

    private TextView mTextView;
    private Button mButton;

    public LocationClient mLocationClient = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mLocationClient = new LocationClient(getApplicationContext());

        mTextView = (TextView) findViewById(R.id.tv_show_info);
        mButton = (Button) findViewById(R.id.btn_get_info);
//        wifiLocation = new WifiLocationManager(MyActivity.this);
//        cellLocation = new CellLocationManager(MyActivity.this);


//        mButton.setOnClickListener(listener);

        startBtn = (Button) findViewById(R.id.startBtn);
        stopBtn = (Button) findViewById(R.id.stopBtn);
        startBtn.setOnClickListener(serviceListener);
        startBtn.setOnClickListener(serviceListener);
//        ManagementFactory

    }

    private OnClickListener serviceListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent myservice = new Intent(getApplicationContext(), MyService.class);
            switch (view.getId()){
                case R.id.startBtn:
                    getApplicationContext().startService(myservice);
                    break;
                case R.id.stopBtn:
                    getApplicationContext().stopService(myservice);
            }

        }
    };


    /*private View.OnClickListener listener = new OnClickListener() {
        @Override
        public void onClick(View view) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    Looper.prepare();
                    mLocationClient.registerLocationListener(new MyLocationListener());    //注册监听函数
                    if (!mLocationClient.isStarted()){
                        MyService.initLocation(mLocationClient);
                        mLocationClient.start();
                        mLocationClient.requestLocation();
                    }
                    Looper.loop();
                }
            }).start();

        }
    };*/


    //启动监听器
    /*private View.OnClickListener listener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            Intent intent=new Intent(MyActivity.this, MyService.class);
            startService(intent);
            switch (v.getId())
            {
                case R.id.startBtn:
                    startService(intent);
                    break;
                case R.id.stopBtn:
                    stopService(intent);
                    break;
                default:
                    break;
            }
        }
    };*/


}
