package com.example.viewmodel29032022;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

public class MyLocationListener implements DefaultLifecycleObserver {

    LocationManager locationManager;
    OnLocationListener onLocationListener;
    Context context;

    public MyLocationListener(Context context) {
        this.context = context;
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onStart(@NonNull LifecycleOwner owner) {
        DefaultLifecycleObserver.super.onStart(owner);
        if (owner.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            if (onLocationListener != null) {
                onLocationListener.onStart();
            }
            locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    1000,
                    0,
                    locationListener
            );
        }
    }

    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(@NonNull Location location) {
            if (onLocationListener != null) {
                onLocationListener.onLocationChanges(location);
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            LocationListener.super.onStatusChanged(provider, status, extras);
        }
    };

    @Override
    public void onStop(@NonNull LifecycleOwner owner) {
        DefaultLifecycleObserver.super.onStop(owner);
        if (onLocationListener != null) {
            onLocationListener.onStop();
            locationManager.removeUpdates(locationListener);
        }
    }

    public void setLocationListener(OnLocationListener locationListener) {
        onLocationListener = locationListener;
    }

    interface OnLocationListener {
        void onStart();

        void onLocationChanges(Location location);

        void onStop();
    }
}
