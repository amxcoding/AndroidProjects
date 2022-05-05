package com.example.alarmemina.Settings;

public class SettingsItem {

    private String mSettingName;
    private String mSettingValue;

    SettingsItem(String name, String value){
        mSettingName = name;
        mSettingValue = value;
    }

    public String getName(){
        return mSettingName;
    }

    public String getValue(){
        return mSettingValue;
    }
}
