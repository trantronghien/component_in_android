package com.example.admin.boundservice;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyService extends Service {
    private final IBinder buckybinder = new MyLocalBinder();
    String msg = "MyService";
    public MyService() {
    }

    @Override
    public void onCreate() {
        Log.i(msg , "onCreate");
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(msg , "onBind");
        return buckybinder;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.i(msg , "onRebind");
        super.onRebind(intent);
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        Log.i(msg , "unbindService");
        super.unbindService(conn);
    }

    @Override
    public void onDestroy() {
        Log.i(msg , "onDestroy");
        super.onDestroy();
    }

    // hàm xử lý chúng ta tự định nghĩa trong service
    public String getCurrentTime(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss" , Locale.US);
        return dateFormat.format(new Date());
    }

    public class MyLocalBinder extends Binder {
        MyService getServices(){
            return MyService.this;
        }
    }

}
