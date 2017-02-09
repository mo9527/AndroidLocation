package com.fly.location;

/**
 * Created by John on 2016/12/7.
 */
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyBroadcastReceiver extends BroadcastReceiver {
    private static final String ACTION = "android.intent.action.BOOT_COMPLETED";
    private boolean isServiceRunning;

    //重写onReceive方法
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("开机自动服务自动启动.....");
        Log.e("自启", "自启动已运行····················");
        Intent myservice = new Intent(context, MyService.class);
        context.startService(myservice);

        if (intent.getAction().equals(Intent.ACTION_TIME_TICK)) {

            //检查Service状态
            ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
                Log.e("类名", service.service.getClassName());
                if ("com.fly.location.MyService".equals(service.service.getClassName())) {
                    isServiceRunning = true;
                }

            }
            if (!isServiceRunning) {
                context.startService(myservice);
            }
        }
    }
}
