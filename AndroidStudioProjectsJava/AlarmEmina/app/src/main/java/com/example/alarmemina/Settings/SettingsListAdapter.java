package com.example.alarmemina.Settings;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.alarm.R;
import com.example.alarmemina.Settings.AlarmSound.AlarmSound;

import java.util.ArrayList;

public class SettingsListAdapter extends ArrayAdapter<SettingsItem> {
    Context mContext;
    int mResource;

    TextView nameTv;
    TextView valueTv;

    View listViewItem;

    public SettingsListAdapter(Context context, int resource, ArrayList<SettingsItem> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        nameTv = convertView.findViewById(R.id.settingsName);
        valueTv = convertView.findViewById(R.id.settingsVal);
        nameTv.setText(getItem(position).getName());
        valueTv.setText(getItem(position).getValue());

        OnClickListener onClickListener = new OnClickListener(position);

        listViewItem = convertView.findViewById(R.id.settingsListViewItem);
        listViewItem.setOnClickListener(onClickListener);

        return convertView;
    }

    private class OnClickListener implements View.OnClickListener{
        int position;

        OnClickListener(int pos){
            position = pos;
        }

        @Override
        public void onClick(View view) {
            String settings = getItem(position).getName();
            if(settings.equals("Alarm sound")) {
                Intent intent = new Intent(mContext, AlarmSound.class);
                intent.putExtra("value", getItem(position).getValue());
                mContext.startActivity(intent);
            }else if(settings.equals("Vibration")){

            }
        }
    }
}
