package com.example.admin.contentprovide.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.admin.contentprovide.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickShowContacts(View view) {
        startActivity(new Intent(MainActivity.this , ShowContacts.class));
    }

    public void onClickShowSms(View view) {
        startActivity(new Intent(MainActivity.this , ShowSms.class));
    }

    public void onClickCreateContentProvider(View view) {
        startActivity(new Intent(MainActivity.this , CreateDataContentProvider.class));
    }
}
