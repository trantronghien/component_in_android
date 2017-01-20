package com.example.admin.contentprovide.Activitys;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.admin.contentprovide.Adapters.SmsAdapter;
import com.example.admin.contentprovide.R;
import com.example.admin.contentprovide.models.Sms;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class ShowSms extends AppCompatActivity {

    public static final String SENT = "content://sms/sent";     // tin nhắn đã gửi
    public static final String INBOX = "content://sms/inbox";   // tin nhắn đến
    public static final String DRAFT = "content://sms/draft";  // đọc từ sms nháp
    SmsAdapter adapterSms;
    ArrayList<Sms> arrSms;
    ListView lvSms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_sms);
        addControls();
        addEvents();
        getSmsFromDevice();
    }

    private void addEvents() {

    }

    private void addControls() {
        arrSms = new ArrayList<>();
        adapterSms = new SmsAdapter(getApplicationContext() , arrSms);
        lvSms = (ListView) findViewById(R.id.listViewSms);
        lvSms.setAdapter(adapterSms);
    }

    private void getSmsFromDevice() {
        Uri uri = Uri.parse(SENT);
        Cursor cursor = getContentResolver().query(uri, null, null ,null,null);
        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
        arrSms.clear();
        while (cursor.moveToNext() ){
            Sms sms = new Sms();
            sms.setBodySms(cursor.getString(cursor.getColumnIndexOrThrow("body")));
            String number = cursor.getString(cursor.getColumnIndexOrThrow("address"));
            sms.setNumber(number);
            sms.setName(getContactName(getApplicationContext() , number));
            long millis = cursor.getLong(cursor.getColumnIndexOrThrow("date"));
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(millis);
            Date finaldate = calendar.getTime();
            String smsDate = finaldate.toString();
            sms.setDuration(smsDate);//cursor.getString(cursor.getColumnIndexOrThrow("date_sent")

            // --- thời gian gửi -----
            calendar.setTimeInMillis(cursor.getLong(cursor.getColumnIndexOrThrow("date_sent")));
            finaldate = calendar.getTime();
            Log.i("datesend" , "" + finaldate.toString());
            arrSms.add(sms);
        }
        cursor.close();
        adapterSms.notifyDataSetChanged();
    }

    // tìm theo số điện thoại nếu có trong danh bạ thì hiện tên
    public String getContactName(Context context, String phoneNumber) {
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI,
                Uri.encode(phoneNumber));
        Cursor cursor = cr.query(uri,
                new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME}, null, null, null);
        if (cursor == null) {
            return null;
        }
        String contactName = null;
        if (cursor.moveToFirst()) {
            contactName = cursor.getString(cursor
                    .getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return contactName;
    }
}
