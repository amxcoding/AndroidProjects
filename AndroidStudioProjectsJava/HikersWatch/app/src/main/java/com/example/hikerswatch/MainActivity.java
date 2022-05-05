package com.example.hikerswatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textViewMap);

        message = "";

        getLocationInfo();
    }

    /*
     Consider calling
     ActivityCompat#requestPermissions
     here to request the missing permissions, and then overriding
     public void onRequestPermissionsResult(int requestCode, String[] permissions,
     int[] grantResults)
     to handle the case where the user grants the permission. See the documentation
     for ActivityCompat#requestPermissions for more details.

     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @org.jetbrains.annotations.NotNull String[] permissions, @NonNull @org.jetbrains.annotations.NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 1, locationListener);
                }
            }
        }
    }

    private void getLocationInfo() {
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                getInfo(location);
                updateTextView();
            }
        };

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 1, locationListener);
            Location lastKnowLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (lastKnowLocation != null) {
                getInfo(lastKnowLocation);
                updateTextView();
            }
        }
    }


    private void getInfo(Location location) {

        String latitude, longitude, accuracy, altitude, address = "Can't find address";

        try {
            Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
            List<Address> addressList = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

            if (addressList != null && addressList.size() > 0) {
                Log.i("Place info", addressList.get(0).toString());

                // getLatitude
                latitude = Double.toString(addressList.get(0).getLatitude());

                // getLongitude
                longitude = Double.toString(addressList.get(0).getLongitude());

                //getAccuracy
                accuracy = Double.toString(location.getAccuracy());

                //getAltitude
                altitude = Double.toString(location.getAltitude());

                if (addressList.get(0).getAddressLine(0) != null) {
                    address = addressList.get(0).getAddressLine(0);
                }

                message = "Latitude: " + latitude + "\n" + "\n"
                        + "Longitude: " + longitude + "\n" + "\n"
                        + "Accuracy: " + accuracy + "\n" + "\n"
                        + "Altitude: " + altitude + "\n" + "\n"
                        + "Address: " + "\n" + address;

                System.out.println(message);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateTextView() {
        textView.setText(message);
    }


}