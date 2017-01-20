package com.example.admin.intentservices;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String fileName = "index.php";
    String pathFile = "https://raw.githubusercontent.com/trantronghien/web_ban_hang_php/master/index.php";
    Button btnDown;

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String pathName = intent.getStringExtra("savepath");
            int result = intent.getIntExtra("resulf" , 0);
            if(result == Activity.RESULT_OK){
                Toast.makeText(context, "Download success " + pathName, Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context, "Download fail !", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(broadcastReceiver , new IntentFilter("com.example.admin.intentservices"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnDown = (Button) findViewById(R.id.button);
        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToIntentService();
            }
        });
    }
    private void sendToIntentService(){
        Intent intent = new Intent(MainActivity.this , MyIntentService.class);
        intent.putExtra(MyIntentService.FILE_NAME , fileName);
        intent.putExtra(MyIntentService.PATH_URL , pathFile);
        startService(intent);
    }
}
