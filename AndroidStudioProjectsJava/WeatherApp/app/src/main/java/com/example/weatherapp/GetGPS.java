package com.example.weatherapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.IOException;
import java.util.Locale;

public class GetGPS extends DownloadJSON {

    public String GPSCityName;
    private LocationManager locationManager;
    public LocationListener locationListener;
    private Geocoder geocoder;
    private String message;

    public void getGPSData() {
        this.message = "";
        this.GPSCityName = "";
        askPermission();
        getGPSCityName();
        updateTextView();
    }

    public void askPermission() {
        locationManager = (LocationManager) MainActivity.MainContextActivity.getActivity().getSystemService(Context.LOCATION_SERVICE);
        geocoder = new Geocoder(MainActivity.MainContextActivity.getActivity(), Locale.getDefault());

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                double latitute = location.getLatitude();
                double longitute = location.getLongitude();
                try {
                    GPSCityName = geocoder.getFromLocation(latitute, longitute, 1).get(0).getAddressLine(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

    }

    public void getGPSCityName() {
        if (ActivityCompat.checkSelfPermission(MainActivity.MainContextActivity.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.MainContextActivity.getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 1, locationListener);
        Location lastKnowLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (lastKnowLocation != null) {
            double latitute = lastKnowLocation.getLatitude();
            double longitute = lastKnowLocation.getLongitude();
            try {
                GPSCityName = geocoder.getFromLocation(latitute, longitute, 1).get(0).getAddressLine(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(GPSCityName);

    }

    public void updateTextView(){
        getGPSCityName();
        if (message.equals("")) {
            Toast.makeText(MainActivity.MainContextActivity.getActivity(), "No permission to get location", Toast.LENGTH_SHORT).show();
        } else {
            MainActivity.textView2.setText(message);
        }
    }
}
