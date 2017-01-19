package com.example.admin.boundservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.admin.boundservice.MyService.MyLocalBinder;

public class MainActivity extends AppCompatActivity {


    MyService buckysservice ;
    boolean isBound = false;

    public void showTime(View view){
        String currentTime = buckysservice.getCurrentTime();
    TextView txttime = (TextView) findViewById(R.id.textview_main_Time);
    txttime.setText(currentTime);
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this , MyService.class);
        bindService(intent, buckysConnection  , Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection buckysConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder mybinder) {
            MyLocalBinder binder = (MyLocalBinder) mybinder;
            buckysservice = binder.getServices();
            isBound = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBound = false;
        }
    };
}
