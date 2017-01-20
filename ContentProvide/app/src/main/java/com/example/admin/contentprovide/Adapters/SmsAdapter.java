package com.example.admin.contentprovide.Adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.contentprovide.R;
import com.example.admin.contentprovide.models.Sms;

import java.util.ArrayList;

/**
 * Created by admin on 1/20/2017.
 */

public class SmsAdapter extends ArrayAdapter<Sms> {

    public SmsAdapter(Context context ,ArrayList arrayList) {
        super(context, R.layout.item_list_sms, arrayList);

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.item_list_sms, null);
        TextView txtname = (TextView) convertView.findViewById(R.id.textname);
        TextView txtbody = (TextView) convertView.findViewById(R.id.textbody);
        TextView txtduration = (TextView) convertView.findViewById(R.id.textduration);
        TextView txtnumber = (TextView) convertView.findViewById(R.id.textnumber);
        Sms sms =  getItem(position);
        if (sms!= null){
            txtname.setText(sms.getName());
            txtbody.setText(sms.getBodySms());
            txtduration.setText(sms.getDuration());
            txtnumber.setText(sms.getNumber());
        }
        return convertView;
    }
}
