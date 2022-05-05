package com.example.alarmemina.Alarm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import com.example.alarm.R;

public class EditAlarm extends AddAlarm {
    static String name;
    static int hour, minutes, state;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_alarm);

        //Get values form bundle
        initValuesFromBundle();

        //Initialize buttons and set onclick listeners
        init();

        //Update recurrence buttons
        updateWeekButtons();

        //Restore last session
        timePicker.setHour(hour);
        timePicker.setMinute(minutes);
    }

    @Override
    public void handleSave() {
        int hour = timePicker.getHour();
        int minutes = timePicker.getMinute();

        mDatabaseHelper.addData(name, hour, minutes, recurrence, state);

        handleOnResult(Activity.RESULT_OK, true);
    }

    @Override
    public void handleCancel(){
        handleOnResult(Activity.RESULT_CANCELED, false);
    }

    @Override
    public void handleOnResult(int result, boolean showMessage){
        Intent returnIntent = new Intent();

        ValidateAlarmTime alarmGoesOfIn = new ValidateAlarmTime(timePicker.getHour(), timePicker.getMinute(), recurrence);
        String message = alarmGoesOfIn.getAlarmGoesOfIn();

        if(showMessage)
            returnIntent.putExtra("NextAlarmIn", message);
        setResult(result, returnIntent);
        finish();
    }

    private void initValuesFromBundle(){
        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("name");
        hour = bundle.getInt("hour");
        minutes = bundle.getInt("minutes");
        recurrence = bundle.getIntegerArrayList("recurrence");
        state = bundle.getInt("state");
    }

    private void init(){
        saveBtn = findViewById(R.id.saveButton);
        cancelBtn = findViewById(R.id.cancelButton);
        deleteBtn = findViewById(R.id.deleteButton);
        timePicker = findViewById(R.id.timePicker);

        deleteBtn.setVisibility(View.VISIBLE);

        OnClickListener listener = new OnClickListener();

        mon = findViewById(R.id.mondayBtn);
        tue = findViewById(R.id.tuesdayBtn);
        wed = findViewById(R.id.wednesdayBtn);
        thu = findViewById(R.id.thursdayBtn);
        fri = findViewById(R.id.fridayBtn);
        sat = findViewById(R.id.saturdayBtn);
        sun = findViewById(R.id.sundayBtn);

        saveBtn.setOnClickListener(listener);
        cancelBtn.setOnClickListener(listener);
        mon.setOnClickListener(listener);
        tue.setOnClickListener(listener);
        wed.setOnClickListener(listener);
        thu.setOnClickListener(listener);
        fri.setOnClickListener(listener);
        sat.setOnClickListener(listener);
        sun.setOnClickListener(listener);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabaseHelper.deleteItem(name);
                handleOnResult(Activity.RESULT_OK, false);
                finish();
            }
        });
    }
}
