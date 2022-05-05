package com.example.alarmemina.Alarm;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.alarm.R;
import com.example.alarmemina.Settings.Settings;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public AlarmListAdapter alarmListAdapter;
    private ListView listView;
    private ArrayList<Alarm> alarms;
    private DatabaseHelper mDatabaseHelper;
    private boolean needsUpdate = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initialize
        mDatabaseHelper = new DatabaseHelper(this);
        alarms = new ArrayList<>();
        listView = findViewById(R.id.listView);

        //Populate listView
        populateListView();

        //Handle on item clicked
        OnItemClickListener itemListener = new OnItemClickListener();
        listView.setOnItemClickListener(itemListener);

        //Handle add new alarm
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddAlarm.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode != 1)
            return;

        if(resultCode == Activity.RESULT_OK){
            needsUpdate = true;
            String message = data.getStringExtra("NextAlarmIn");
            if(message != null)
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }
    }

    class OnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Alarm alarm  = (Alarm) listView.getItemAtPosition(i);
            Intent intent = new Intent(MainActivity.this, EditAlarm.class);
            intent.putExtra("name", alarm.getName());
            intent.putExtra("hour", alarm.mHour);
            intent.putExtra("minutes", alarm.mMinutes);
            intent.putIntegerArrayListExtra("recurrence", alarm.getRecurrenceDays());
            intent.putExtra("state", alarm.getState());
            startActivityForResult(intent, 1);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(!needsUpdate)
            return;

        populateListView();
        alarmListAdapter.notifyDataSetChanged();
    }

    private void populateListView(){
        Cursor data = mDatabaseHelper.getData();

        dismissAllAlarms();
        alarms.clear();

        while(data.moveToNext()){
            String name = data.getString(0);
            int hour = data.getInt(1);
            int minutes = data.getInt(2);

            ArrayList<Integer> recurrence = new ArrayList<>();
            for (int i = 3; i < 10; i++){
                recurrence.add(data.getInt(i));
            }

            Alarm alarm = new Alarm(this, name, hour, minutes, recurrence, data.getInt(10));
            alarms.add(alarm);
        }

        alarmListAdapter = new AlarmListAdapter(this, R.layout.list_item, alarms);
        alarmListAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
            }
        });
        listView.setAdapter(alarmListAdapter);
        needsUpdate = false;
    }

    private void dismissAllAlarms(){
        for(int i=0; i < alarms.size(); i++){
            alarms.get(i).dismissAllAlarms();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, Settings.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
