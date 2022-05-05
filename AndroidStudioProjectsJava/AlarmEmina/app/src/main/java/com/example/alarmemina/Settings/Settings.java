package com.example.alarmemina.Settings;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.alarm.R;

import java.util.ArrayList;

public class Settings extends AppCompatActivity {

    SettingsListAdapter settingsListAdapter;
    ArrayList<SettingsItem> settingsArray = new ArrayList<>();

    ListView settingsListView;
    DataBaseHelperSettings mDataBaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        mDataBaseHelper = new DataBaseHelperSettings(this);

        fillInData();

        settingsListAdapter = new SettingsListAdapter(this, R.layout.settings_item, settingsArray);
        settingsListView = findViewById(R.id.listViewSettings);
        settingsListView.setAdapter(settingsListAdapter);

    }

    private void fillInData(){
        Cursor data = mDataBaseHelper.getData();
        data.moveToNext();

        settingsArray.clear();

        //Create settings items
        settingsArray.add(new SettingsItem("Alarm sound", data.getString(1)));
        settingsArray.add(new SettingsItem("Vibration", data.getString(2)));
        settingsArray.add(new SettingsItem("Snooze", data.getString(3)));
        settingsArray.add(new SettingsItem("Mission", data.getString(4)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        fillInData();
        settingsListAdapter.notifyDataSetChanged();
    }
}
