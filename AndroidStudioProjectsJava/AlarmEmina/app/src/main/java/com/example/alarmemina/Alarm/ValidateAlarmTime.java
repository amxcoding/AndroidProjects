package com.example.alarmemina.Alarm;

import android.app.AlarmManager;
import android.text.format.DateUtils;
import java.util.ArrayList;
import java.util.Calendar;

public class ValidateAlarmTime {
    int mHour, mMinutes;
    ArrayList<Integer> mRecurrence;
    ArrayList<Calendar> calList = new ArrayList<>();

    ValidateAlarmTime(int hour, int minutes, ArrayList<Integer> recurrence){
        mHour = hour;
        mMinutes = minutes;
        mRecurrence = recurrence;

        if(!recurrence.contains(1))
            calList.add(validateAndSetCalendarToWeekday(-1));

        for (int i = 0; i < 7; i++){
            int dayOfweek = i+2 % 7 + 1;
            if(mRecurrence.get(i) == 1)
                calList.add(validateAndSetCalendarToWeekday(dayOfweek));
        }
    }

    public ArrayList<Calendar> getCalList(){
        return calList;
    }

    private Calendar validateAndSetCalendarToWeekday(int dayWeek){
        Calendar cal = Calendar.getInstance();
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
        return cal;
    }

    public String getAlarmGoesOfIn(){
        Calendar cal = calList.get(0);

        for (int i = 0; i < calList.size(); i++){
            if(cal.getTimeInMillis() > calList.get(i).getTimeInMillis())
                cal = calList.get(i);
        }

        long timeDiff = cal.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
        long differenceInSeconds = timeDiff / DateUtils.SECOND_IN_MILLIS;

        long min = (differenceInSeconds % (60 * 60)) / 60;
        long hour = (differenceInSeconds % (60 * 60 * 24)) / (60 * 60);
        long days = (differenceInSeconds % (60 * 60 * 24 * 7)) / (60 * 60 * 24);

        String returnVal;

        if(days == 0)
            returnVal = "Alarm set " +hour+ " hours and " +min+ " minutes from now.";
        else
            returnVal = "Alarm set " +days+ " days " +hour+ " hours and " +min+ " minutes from now.";

        return returnVal;
    }
}
