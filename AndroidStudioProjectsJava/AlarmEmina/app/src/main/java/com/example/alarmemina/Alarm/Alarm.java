package com.example.alarmemina.Alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;

import com.example.alarmemina.HandleAlarm.AlarmReceiver;
import com.example.alarmemina.Settings.DataBaseHelperSettings;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Alarm {
    public String mName;
    public int mHour;
    public int mMinutes;
    public ArrayList<Integer> mRecurrenceDays;
    private int mState;
    public Context mContext;
    private Calendar cal;
    ArrayList<PendingIntent> pendingIntentList = new ArrayList<>();
    ArrayList<AlarmManager> alarmMgrList = new ArrayList<>();
    private AlarmManager alarmMgr;
    PendingIntent pendingIntent;
    int SERVICE_ID;
    Intent alarmReceiverIntent;
    DataBaseHelperSettings dataBaseHelperSettings;
    String alarmSound;

    Alarm(Context context, String name, int alarmHour, int alarmMinute, ArrayList<Integer> recurrenceDays, int state){
        mName = name;
        mHour = alarmHour;
        mMinutes = alarmMinute;
        mRecurrenceDays = recurrenceDays;
        mState = state;
        mContext = context;
        SERVICE_ID = Integer.parseInt(name.replaceAll("[\\D]", ""));
        cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, mHour);
        cal.set(Calendar.MINUTE, mMinutes);
        cal.set(Calendar.SECOND, 0);

        dataBaseHelperSettings = new DataBaseHelperSettings(mContext);
        Cursor data = dataBaseHelperSettings.getData();
        data.moveToNext();

        alarmSound = data.getString(1);

        alarmMgr = (AlarmManager)mContext.getSystemService(Context.ALARM_SERVICE);
        alarmReceiverIntent = new Intent(mContext, AlarmReceiver.class);
        alarmReceiverIntent.putExtra("sound", alarmSound);
        pendingIntent = PendingIntent.getBroadcast(mContext, -1, alarmReceiverIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        for(int i = 0; i < 7; i++)
        {
            alarmMgrList.add((AlarmManager)mContext.getSystemService(Context.ALARM_SERVICE));
            pendingIntentList.add(PendingIntent.getBroadcast(mContext, i, alarmReceiverIntent, PendingIntent.FLAG_UPDATE_CURRENT));
        }

        UpdateAlarmState();
    }



    public String getName(){ return mName; }

    public ArrayList<Integer> getRecurrenceDays() { return mRecurrenceDays; }

    public String getTime(){
        Time time = new Time(mHour, mMinutes,0);
        SimpleDateFormat sdf = new SimpleDateFormat("kk:mm");
        String date = sdf.format(time.getTime());
        return date;
    }

    private void validateAndSetCalendarToWeekday(int dayWeek){
        cal = Calendar.getInstance();
        if(dayWeek != -1)
            cal.set(Calendar.DAY_OF_WEEK, dayWeek);
        cal.set(Calendar.HOUR_OF_DAY, mHour);
        cal.set(Calendar.MINUTE, mMinutes);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        long start = cal.getTimeInMillis();
        if (cal.before(Calendar.getInstance())) {
            if(dayWeek != -1)
                start += AlarmManager.INTERVAL_DAY * 7;
            else
                start += AlarmManager.INTERVAL_DAY;
            cal.setTimeInMillis(start);
        }
    }

    public void setAndUpdateState(int state){
        SetAlarmState(state);
        UpdateAlarmState();
    }

    private void SetAlarmState(int state){
        if(mState != state)
            mState = state;
    }

    public int getState(){
        return mState;
    }

    private void UpdateAlarmState(){
        if(mState == 0) {
            alarmReceiverIntent.putExtra("state", 0);
            dismissAllAlarms();
        } else {
            alarmReceiverIntent.putExtra("state", 1);
            setAlarm();
        }

        pendingIntent = PendingIntent.getBroadcast(mContext, -1, alarmReceiverIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        for(int i = 0; i< 7; i++){
            pendingIntentList.set(i, PendingIntent.getBroadcast(mContext, i, alarmReceiverIntent, PendingIntent.FLAG_UPDATE_CURRENT));
        }
    }

    public void dismissAllRecurrenceAlarms(){
        for(int i = 0; i < 7; i++){
            alarmMgrList.get(i).cancel(pendingIntentList.get(i));
        }
    }

    public void dismissAllAlarms(){
        dismissAllRecurrenceAlarms();
        alarmMgr.cancel(pendingIntent);
    }

    public void setAlarm(){
        if(!mRecurrenceDays.contains(1))
        {
            dismissAllRecurrenceAlarms();
            setAlarmFor(-1);
            return;
        }

        alarmMgr.cancel(pendingIntent);

        for(int i = 0; i < 7; i++){
            int dayOfWeek = (i + 1) % 7 + 1;
            if(mRecurrenceDays.get(i)==1){
                setAlarmFor(dayOfWeek);
            }else{
                dismissAlarmForWeekday(dayOfWeek);
            }
        }
    }

    private void setAlarmFor(int weekday){
        validateAndSetCalendarToWeekday(weekday);

        if(weekday == -1){
            alarmMgr.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
            return;
        }

        int positionWeekDay = weekday - 1;
        alarmMgrList.get(positionWeekDay).setRepeating(AlarmManager.RTC_WAKEUP,
                cal.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 7, pendingIntentList.get(positionWeekDay));
    }

    private void dismissAlarmForWeekday(int weekday){
        int positionWeekDay = weekday - 1;
        alarmMgrList.get(positionWeekDay).cancel(pendingIntentList.get(positionWeekDay));
    }
}
