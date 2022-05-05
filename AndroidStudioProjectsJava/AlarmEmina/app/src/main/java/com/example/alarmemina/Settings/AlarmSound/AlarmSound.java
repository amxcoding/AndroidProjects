package com.example.alarmemina.Settings.AlarmSound;

import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.alarm.R;
import com.example.alarmemina.Settings.DataBaseHelperSettings;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class AlarmSound extends AppCompatActivity {
    SoundListAdapter soundAdapter;
    ArrayList<Sound> soundOptions;
    ListView listViewSounds;
    DataBaseHelperSettings dataBaseHelperSettings;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_sound);

        String savedValue = getIntent().getStringExtra("value");

        dataBaseHelperSettings = new DataBaseHelperSettings(this);
        final Cursor data = dataBaseHelperSettings.getData();
        data.moveToNext();

        soundOptions = new ArrayList<>();

        Field[] fields=R.raw.class.getFields();
        for(int count=0; count < fields.length; count++){

            soundOptions.add(new Sound(fields[count].getName(), savedValue.equals(fields[count].getName())));
        }

        soundOptions.add(new Sound("random", savedValue.equals("random")));

        soundAdapter = new SoundListAdapter(this, R.layout.options_item, soundOptions);
        soundAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                int i = 0;
                while(!soundOptions.get(i).IsChecked())
                    i++;
                dataBaseHelperSettings.addData(data.getString(0), soundOptions.get(i).GetName(), data.getString(2), data.getString(3), data.getString(4));
            }
        });
        listViewSounds = findViewById(R.id.listViewSounds);
        listViewSounds.setAdapter(soundAdapter);
    }


}
