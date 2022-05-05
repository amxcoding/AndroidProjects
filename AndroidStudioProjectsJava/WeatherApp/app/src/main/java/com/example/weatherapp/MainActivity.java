package com.example.weatherapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    DownloadJSON downloadJSON;
    GetGPS getGPS;
    EditText cityName;
    MainContextActivity mainContextActivity;

    @SuppressLint("StaticFieldLeak")
    static TextView textView;
    static TextView textView2;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainContextActivity = new MainContextActivity(this,this);
        checkPermission();
        setContentView(R.layout.activity_main);
        downloadJSON = new DownloadJSON();
        getGPS = new GetGPS();
        getGPS.getGPSData();
        cityName = findViewById(R.id.editTextCityName);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        fillWithGPSData(textView2);

    }

    public void getData(String cityName) {
        try {
            String encodedCityName = URLEncoder.encode(cityName, "UTF-8");
            String url = "https://api.openweathermap.org/data/2.5/weather?q=" + encodedCityName + "&appid=472b068ec8d4b42703b6fc25fc0e2bca";
            downloadJSON.execute(url);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                return;
            }
        }
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            return;
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
    }

    private String getCityName() {
        return cityName.getText().toString().toLowerCase();
    }

    public void search(View view) {

        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(cityName.getWindowToken(), 0);

        if (getCityName().matches("")) {
            Toast.makeText(this, "Type in a city name", Toast.LENGTH_SHORT).show();
        } else {
            getData(getCityName());
        }
    }

    public void fillWithGPSData(View view) {

        if (getGPS.GPSCityName.matches("")) {
            Toast.makeText(this, "Type in a city name", Toast.LENGTH_SHORT).show();
        } else {
            getData(getGPS.GPSCityName);
        }
    }

    public static class MainContextActivity{
        private static Context context;
        private static Activity activity;

        public MainContextActivity(Context context, Activity activity) {
            this.context = context;
            this.activity = activity;
        }

        public static Context getContext() {
            return context;
        }

        public static Activity getActivity() {
            return activity;
        }
    }
}
