package com.example.alarmemina.HandleAlarm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.alarm.R;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HandleAlarm extends AppCompatActivity {
    Button dismissButton;
    TextView alarmTimeTv;
    static Intent mPlayingRingtone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm);

        dismissButton = findViewById(R.id.dismissAlarm);
        alarmTimeTv = findViewById(R.id.alarmTimeTv);

        mPlayingRingtone = new Intent(this, RingtonePlayingService.class);
        String sound = getIntent().getExtras().getString("sound");
        mPlayingRingtone.putExtra("sound", sound);

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        String timeString = formatter.format(new Date(Calendar.getInstance().getTimeInMillis()));
        alarmTimeTv.setText(timeString);

        OnAlarmDismissListener listener = new OnAlarmDismissListener();
        dismissButton.setOnClickListener(listener);

        getApplicationContext().startService(mPlayingRingtone);

    }

    public class OnAlarmDismissListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            getApplicationContext().stopService(mPlayingRingtone);
            onBackPressed();
        }
    }
}
