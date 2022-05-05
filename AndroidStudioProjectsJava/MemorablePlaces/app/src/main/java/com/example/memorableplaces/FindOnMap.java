package com.example.memorableplaces;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.memorableplaces.databinding.ActivityFindOnMapBinding;

import java.util.List;
import java.util.Locale;

public class FindOnMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityFindOnMapBinding binding;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private Geocoder geocoder;
    private Marker userLocationMarker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityFindOnMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @org.jetbrains.annotations.NotNull String[] permissions, @NonNull @org.jetbrains.annotations.NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 100, locationListener);
                    Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                    String address = getInfoOfLocation(lastKnownLocation)[2];
                    updateUserLocationOnMap(lastKnownLocation, address, true);
                }
            }
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        updateMapWithMarkers();

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                String address = getInfoOfLocation(location)[2];
                updateUserLocationOnMap(location, address, false);
            }
        };

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            if (MainActivity.isPressed()) {
                goToLocation(MainActivity.getLatitudeDouble(), MainActivity.getLongitudeDouble());
                MainActivity.setPressed(false);
            } else {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 100, locationListener);

                Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                String address = getInfoOfLocation(lastKnownLocation)[2];
                updateUserLocationOnMap(lastKnownLocation, address, true);
            }
        }

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                Location location = new Location(LocationManager.GPS_PROVIDER);
                location.setLatitude(latLng.latitude);
                location.setLongitude(latLng.longitude);
                String address = getInfoOfLocation(location)[2];

                MainActivity.getLatitudeList().add(latLng.latitude);
                MainActivity.getLongitudeList().add(latLng.longitude);
                MainActivity.getAddressesList().add(address);
                MainActivity.saveArray();

                mMap.addMarker(new MarkerOptions().position(latLng).title(address).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
                Toast.makeText(getApplicationContext(), address, Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void goToLocation(double latitude, double longitude) {
        LatLng latLng = new LatLng(latitude, longitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
    }

    private void updateMapWithMarkers() {
        mMap.clear();

        for (int i = 0; i <= MainActivity.getAddressesList().size() - 1; i++) {
            double latitude = MainActivity.getLatitudeList().get(i);
            double longitude = MainActivity.getLongitudeList().get(i);
            String address = MainActivity.getAddressesList().get(i);

            LatLng latLng = new LatLng(latitude, longitude);
            Location location = new Location(LocationManager.GPS_PROVIDER);
            location.setLatitude(latitude);
            location.setLongitude(longitude);

            mMap.addMarker(new MarkerOptions().position(latLng).title(address).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
        }
    }

    private void updateUserLocationOnMap(Location location, String address, boolean zoom) {
        if (userLocationMarker != null) {
            userLocationMarker.remove();
        }
        //Toast.makeText(getApplicationContext(), address, Toast.LENGTH_SHORT).show();
        if (location != null) {
            LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());
            userLocationMarker = mMap.addMarker(new MarkerOptions().position(userLocation).title(address).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            if (zoom) {
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 10));
            } else {
                mMap.moveCamera(CameraUpdateFactory.newLatLng(userLocation));
            }
        }
    }


    private String[] getInfoOfLocation(Location location) {
        String[] locationInfo = new String[3];
        String latitude, longitude, address = "Can't find address.";

        if (location != null) {
            try {
                List<Address> addressList = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

                if (addressList != null && addressList.size() > 0) {
                    Log.i("Place info", addressList.get(0).toString());

                    // getLatitude
                    latitude = Double.toString(addressList.get(0).getLatitude());
                    locationInfo[0] = latitude;

                    // getLongitude
                    longitude = Double.toString(addressList.get(0).getLongitude());
                    locationInfo[1] = longitude;

                    if (addressList.get(0).getAddressLine(0) != null) {
                        address = addressList.get(0).getAddressLine(0);
                        locationInfo[2] = address;
                    }

                    System.out.println(address);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return locationInfo;
    }


}