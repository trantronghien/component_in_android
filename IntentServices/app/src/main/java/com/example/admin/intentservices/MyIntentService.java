package com.example.admin.intentservices;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MyIntentService extends IntentService {

    // tạo các key để chuyển Intents
    public static final String PATH_URL = "urlpath";
    public static final String FILE_NAME = "filename";

    public static int resulthandle = Activity.RESULT_CANCELED;

    public MyIntentService() {
        super("MyIntentService");
    }



    @Override
    protected void onHandleIntent(Intent intent) {
        String pathUrl =  intent.getStringExtra(PATH_URL);
        String fileName = intent.getStringExtra(FILE_NAME);

        File file = new File(String.valueOf(Environment.getExternalStorageDirectory()), fileName);
        if (file.exists()){
            file.delete();
        }
        FileOutputStream fileOutputStream ;
        try {
            URL url = new URL(pathUrl);
            InputStream stream = url.openConnection().getInputStream();
            InputStreamReader reader = new InputStreamReader(stream);
            fileOutputStream = new FileOutputStream(file.getPath());
            int next;
            while ((next = reader.read()) != -1){
                Log.i("info" , "" + next);
                fileOutputStream.write(next);
            }
            Log.i("infos" , "finsh");
            fileOutputStream.close();
            stream.close();
            reader.close();
            resulthandle =  Activity.RESULT_OK;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SendDataUsingBroadcast(pathUrl , resulthandle);
    }

    public void SendDataUsingBroadcast(String path , int result){
        Intent intent = new Intent("com.example.admin.intentservices");
        intent.putExtra("savepath" , path);
        intent.putExtra("resulf" , result);
        sendBroadcast(intent);
    }

}
