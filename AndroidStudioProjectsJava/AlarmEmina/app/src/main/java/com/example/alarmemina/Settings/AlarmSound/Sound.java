package com.example.alarmemina.Settings.AlarmSound;

public class Sound {
    String mName;
    boolean mChecked;

    Sound(String name, boolean checked){
        mName = name;
        mChecked = checked;
    }

    public String GetName(){
        return mName;
    }

    public boolean IsChecked(){
        return mChecked;
    }

    public void SetChecked(boolean checked){
        mChecked = checked;
    }
}
