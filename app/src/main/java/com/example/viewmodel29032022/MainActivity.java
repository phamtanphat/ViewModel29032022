package com.example.viewmodel29032022;

import android.Manifest;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView tvLocation;
    MyLocationListener myLocationListener;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvLocation = findViewById(R.id.text_view_location);

        ActivityResultLauncher<String[]> locationPermissionRequest = registerForActivityResult(
                new ActivityResultContracts.RequestMultiplePermissions(),
                result -> {
                    Boolean fineLocationGranted = result.getOrDefault(
                            Manifest.permission.ACCESS_FINE_LOCATION, false);
                    Boolean coarseLocationGranted = result.getOrDefault(
                            Manifest.permission.ACCESS_COARSE_LOCATION, false);
                    if (fineLocationGranted != null && fineLocationGranted || coarseLocationGranted != null && coarseLocationGranted) {
                        myLocationListener = new MyLocationListener(getApplicationContext());
                        getLifecycle().addObserver(myLocationListener);
                        myLocationListener.setLocationListener(new MyLocationListener.OnLocationListener() {
                            @Override
                            public void onStart() {
                                Log.d("BBB","Start");
                            }

                            @Override
                            public void onLocationChanges(Location location) {
                                Log.d("BBB","Lat: " + location.getLatitude() + ", Lon: " + location.getLatitude());
                                tvLocation.setText("Lat: " + location.getLatitude() + ", Lon: " + location.getLatitude());
                            }

                            @Override
                            public void onStop() {
                                Toast.makeText(MainActivity.this, "Stop", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        Toast.makeText(this, "Permission deny", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        locationPermissionRequest.launch(new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        });
    }
}
