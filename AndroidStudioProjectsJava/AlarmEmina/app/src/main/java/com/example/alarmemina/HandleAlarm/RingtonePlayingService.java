package com.example.alarmemina.HandleAlarm;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import androidx.annotation.Nullable;
import com.example.alarm.R;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;

public class RingtonePlayingService extends Service {

    static MediaPlayer player;
    ArrayList<Integer> songs;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String sound = intent.getExtras().getString("sound");

        songs = new ArrayList<>();
        Field[] fields = R.raw.class.getFields();
        int count = 0;
        int songCount = 0;

        while(count < fields.length){
            if(sound.equals(fields[count].getName()))
                songCount = count;
            songs.add(fields[count].getModifiers());
            count ++;
        }

        if(sound.equals("random")){
            Random random = new Random();
            player = MediaPlayer.create(this,
                    getResources().getIdentifier(fields[random.nextInt(songs.size())].getName(), "raw", getPackageName()));
        }else{
            player = MediaPlayer.create(this,
                    getResources().getIdentifier(fields[songCount].getName(), "raw", getPackageName()));
        }

        player.setLooping(true);
        player.start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        player.stop();
        super.onDestroy();
    }
}
