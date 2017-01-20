package com.example.admin.contentprovide.Activitys;

import android.annotation.TargetApi;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.admin.contentprovide.R;
import com.example.admin.contentprovide.models.Contacts;

import java.util.ArrayList;

public class ShowContacts extends AppCompatActivity {

    ArrayAdapter<Contacts> adapter ;
    ArrayList<Contacts> arrContacts;
    ListView listContacts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contacts);
        addControls();
        addEvents();
        getContactsFromDevice();

    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void getContactsFromDevice() {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Uri uri1 = Telephony.Sms.Inbox.CONTENT_URI;
        String uris= uri1.toString();
        Cursor cursor = getContentResolver().query(uri , null , null , null , null);
        arrContacts.clear();
        // lấy tên cột sau đó lấy index của cột dữ liệu
        while (cursor.moveToNext()){
            String columnName = ContactsContract.Contacts.DISPLAY_NAME;
            String columnPhone = ContactsContract.CommonDataKinds.Phone.NUMBER;
            int indexColumnName = cursor.getColumnIndex(columnName);
            int indexColumnPhone = cursor.getColumnIndex(columnPhone);
            // lấy dữ liệu từ cột có index
            String name = cursor.getString(indexColumnName);
            String phone = cursor.getString(indexColumnPhone);
            Contacts contacts = new Contacts(name , phone);
            arrContacts.add(contacts);
        }
        cursor.close();
        adapter.notifyDataSetChanged();
    }

    private void addControls() {
        listContacts = (ListView) findViewById(R.id.listcontacts);
        arrContacts = new ArrayList<>();
        adapter = new ArrayAdapter<Contacts>(ShowContacts.this , android.R.layout.simple_list_item_1 , arrContacts);
        listContacts.setAdapter(adapter);
    }

    private void addEvents() {

    }

}
