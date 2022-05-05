package com.example.alarmemina.Alarm;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import com.example.alarm.R;
import java.util.ArrayList;
import java.util.Arrays;

public class AlarmListAdapter extends ArrayAdapter<Alarm> {
    public Context mContext;
    private int mResource;
    TextView mon, tue, wed, thu, fri, sat, sun;
    private Switch aSwitch;
    private Alarm alarm;

    public AlarmListAdapter(Context context, int resource, ArrayList<Alarm> objects){
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @Nullable
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        //Initialize textView and update view
        initAndUpdate(position, convertView);

        return convertView;
    }

    private void initAndUpdate(int position, View convertView){
        alarm = getItem(position);
        TextView textViewItem = convertView.findViewById(R.id.time);
        textViewItem.setText(alarm.getTime());

        aSwitch = convertView.findViewById(R.id.switch1);
        OnCheckedChangeListener listener = new OnCheckedChangeListener();
        aSwitch.setOnCheckedChangeListener(listener);

        mon = convertView.findViewById(R.id.monday);
        tue = convertView.findViewById(R.id.tuesday);
        wed = convertView.findViewById(R.id.wednesday);
        thu = convertView.findViewById(R.id.thursday);
        fri = convertView.findViewById(R.id.friday);
        sat = convertView.findViewById(R.id.saturday);
        sun = convertView.findViewById(R.id.sunday);

        updateRecurrenceText(position);

        if(alarm.getState() == 0){
            aSwitch.setChecked(false);
        }else{
            aSwitch.setChecked(true);
        }
    }

    class OnCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if(b){
                alarm.setAndUpdateState(1);
                SendMessage(alarm);
            }else{
                alarm.setAndUpdateState(0);
            }
            DatabaseHelper databaseHelper = new DatabaseHelper(mContext);
            databaseHelper.addData(alarm.getName(), alarm.mHour, alarm.mMinutes, alarm.getRecurrenceDays(), alarm.getState());
        }
    }

    private void SendMessage(Alarm alarm){
        ValidateAlarmTime validateAlarmTime = new ValidateAlarmTime(alarm.mHour, alarm.mMinutes, alarm.mRecurrenceDays);
        Toast.makeText(mContext, validateAlarmTime.getAlarmGoesOfIn(), Toast.LENGTH_LONG).show();
    }

    private void updateRecurrenceText(int position){
        Alarm alarm = getItem(position);
        ArrayList<Integer> recurrence = alarm.getRecurrenceDays();
        if(recurrence == null)
            return;
        ArrayList<TextView> viewList = new ArrayList<>();
        viewList.addAll(Arrays.asList(mon, tue, wed, thu, fri, sat, sun));

        for (int i = 0; i < 7; i++){
            TextView tv = viewList.get(i);
            if(recurrence.get(i)==0){
                tv.setTypeface(null, Typeface.NORMAL);
                tv.setTextColor(mContext.getResources().getColor(android.R.color.tab_indicator_text));
            }else{
                tv.setTypeface(null, Typeface.BOLD);
                tv.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
            }
        }
    }
}
