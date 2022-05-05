package com.example.alarmemina.Alarm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import androidx.appcompat.app.AppCompatActivity;
import com.example.alarm.R;
import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;

public class AddAlarm extends AppCompatActivity {

    protected Button saveBtn, cancelBtn, deleteBtn,
            mon, tue, wed, thu, fri, sat, sun;
    protected TimePicker timePicker;
    protected DatabaseHelper mDatabaseHelper;
    public ArrayList<Integer> recurrence;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_alarm);

        init();
        setOnClickListener();
    }

    private void init(){
        mDatabaseHelper = new DatabaseHelper(this);
        recurrence = new ArrayList<>();

        for (int i = 0; i < 7 ; i++){
            recurrence.add(0);
        }

        saveBtn = findViewById(R.id.saveButton);
        cancelBtn = findViewById(R.id.cancelButton);
        deleteBtn = findViewById(R.id.deleteButton);
        timePicker = findViewById(R.id.timePicker);

        deleteBtn.setVisibility(View.INVISIBLE);

        mon = findViewById(R.id.mondayBtn);
        tue = findViewById(R.id.tuesdayBtn);
        wed = findViewById(R.id.wednesdayBtn);
        thu = findViewById(R.id.thursdayBtn);
        fri = findViewById(R.id.fridayBtn);
        sat = findViewById(R.id.saturdayBtn);
        sun = findViewById(R.id.sundayBtn);
    }

    private void setOnClickListener(){
        OnClickListener listener = new OnClickListener();

        saveBtn.setOnClickListener(listener);
        cancelBtn.setOnClickListener(listener);
        mon.setOnClickListener(listener);
        tue.setOnClickListener(listener);
        wed.setOnClickListener(listener);
        thu.setOnClickListener(listener);
        fri.setOnClickListener(listener);
        sat.setOnClickListener(listener);
        sun.setOnClickListener(listener);
    }

    class OnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.cancelButton:
                    handleCancel();
                    break;
                case R.id.saveButton:
                    handleSave();
                    break;
                case R.id.mondayBtn:
                    if(recurrence.get(0) == 0){
                        recurrence.set(0, 1);
                    }else{
                        recurrence.set(0, 0);
                    }
                    break;
                case R.id.tuesdayBtn:
                    if(recurrence.get(1) == 0){
                        recurrence.set(1, 1);
                    }else{
                        recurrence.set(1, 0);
                    }
                    break;
                case R.id.wednesdayBtn:
                    if(recurrence.get(2) == 0){
                        recurrence.set(2, 1);
                    }else{
                        recurrence.set(2, 0);
                    }
                    break;
                case R.id.thursdayBtn:
                    if(recurrence.get(3) == 0){
                        recurrence.set(3, 1);
                    }else{
                        recurrence.set(3, 0);
                    }
                    break;
                case R.id.fridayBtn:
                    if(recurrence.get(4) == 0){
                        recurrence.set(4, 1);
                    }else{
                        recurrence.set(4, 0);
                    }
                    break;
                case R.id.saturdayBtn:
                    if(recurrence.get(5) == 0){
                        recurrence.set(5, 1);
                    }else{
                        recurrence.set(5, 0);
                    }
                    break;
                case R.id.sundayBtn:
                    if(recurrence.get(6) == 0){
                        recurrence.set(6, 1);
                    }else{
                        recurrence.set(6, 0);
                    }
                    break;
            }
            updateWeekButtons();
        }
    }

    protected void updateWeekButtons(){

        if(recurrence.get(0) == 1){
            mon.setActivated(true);
        }else{
            mon.setActivated(false);
        }

        if(recurrence.get(1) == 1){
            tue.setActivated(true);
        }else{
            tue.setActivated(false);
        }

        if(recurrence.get(2) == 1){
            wed.setActivated(true);
        }else{
            wed.setActivated(false);
        }

        if(recurrence.get(3) == 1){
            thu.setActivated(true);
        }else{
            thu.setActivated(false);
        }

        if(recurrence.get(4) == 1){
            fri.setActivated(true);
        }else{
            fri.setActivated(false);
        }

        if(recurrence.get(5) == 1){
            sat.setActivated(true);
        }else{
            sat.setActivated(false);
        }

        if(recurrence.get(6) == 1){
            sun.setActivated(true);
        }else{
            sun.setActivated(false);
        }
    }

    public void handleSave(){
        int hour = timePicker.getHour();
        int minutes = timePicker.getMinute();
        String newName = "Alarm" + hour + minutes;

        mDatabaseHelper.addData(newName, hour, minutes, recurrence, 1);

        handleOnResult(Activity.RESULT_OK, true);
    }

    public void handleCancel(){
        handleOnResult(Activity.RESULT_CANCELED, false);
    }


    public void handleOnResult(int result, boolean showMessage){
        Intent returnIntent = new Intent();

        ValidateAlarmTime alarmGoesOfIn = new ValidateAlarmTime(timePicker.getHour(), timePicker.getMinute(), recurrence);
        String message = alarmGoesOfIn.getAlarmGoesOfIn();

        if(showMessage)
            returnIntent.putExtra("NextAlarmIn", message);
        setResult(result, returnIntent);
        finish();
    }
}

